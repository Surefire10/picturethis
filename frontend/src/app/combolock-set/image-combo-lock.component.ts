import { AfterViewInit, Component, ElementRef, OnInit, Renderer2, ViewChild, } from '@angular/core';
import { RetrieveImageChunksService } from '../services/retrieve-image-chunks.service';
import { IImage } from '../IImage';
import { filter } from 'rxjs/operators'
import { Observable } from 'rxjs';
import { NavigationEnd, Router } from '@angular/router';
import { HandleUserComboLockService } from '../services/handle-user-combo-lock.service';




@Component({

  selector: 'app-image-combo-lock',
  templateUrl: './image-combo-lock.component.html',
  styleUrls: ['./image-combo-lock.component.css']
})



export class ImageComboLockComponent implements OnInit, AfterViewInit {

  @ViewChild("userImages") userImages! : ElementRef<HTMLElement>
  @ViewChild("grid") grid! : ElementRef<HTMLImageElement>

  


  constructor(private retrieveImageChunksService :RetrieveImageChunksService,
              private handleUserComboLockService : HandleUserComboLockService,
              private renderer: Renderer2,
              private router: Router){
                this.router.events
                .pipe(filter((rs): rs is NavigationEnd => rs instanceof NavigationEnd))
                .subscribe(event => {
                  if (
                    event.id === 1 &&
                    event.url === event.urlAfterRedirects
                  ) {
                    this.router.navigate(["user/signup"]);
                    alert("Please Sign up Again")
                  }
                })
              }

     
     images!: Observable<IImage[]>
     arrayOfImagesInGrid: Array<string> = []
     filledGridTileCounter:number = 0



  ngOnInit() {

    this.images = this.retrieveImageChunksService.getImageChunks()
    
    
    


    }

  ngAfterViewInit(){

    

    this.renderer.listen(this.userImages!.nativeElement, "dragstart",this.onImageDragged.bind(this))
    
    this.renderer.listen(this.grid!.nativeElement, "dragstart",(event) => {event.preventDefault()})
    this.renderer.listen(this.grid!.nativeElement, "dragenter",(event) => {event.preventDefault()})
    this.renderer.listen(this.grid!.nativeElement, "dragover", (event) => {event.preventDefault()})
    this.renderer.listen(this.grid!.nativeElement, "drop",this.onCellFilled.bind(this))


    }

  

  onImageDragged(event: any){
    
    event.dataTransfer!.setData("image_source",event.target.currentSrc)
    event.dataTransfer!.setData("image_index",event.target.className)    

  }

  

  onCellFilled(event: any){


    //cell coordinates are read row column 

    const droppedImageIndex = event.dataTransfer.getData("image_index") 
    const droppedImageSource = event.dataTransfer.getData("image_source")
    const targetClassName = event.target.className.toString()
    const cellCoordinates = event.target.className
    
    var imagesFromUser = this.userImages.nativeElement.getElementsByTagName("img")



    

    if(!this.arrayOfImagesInGrid.includes(droppedImageSource) && (!targetClassName.includes("grid"))){

      event.target.src = droppedImageSource 

      this.arrayOfImagesInGrid.push(droppedImageSource)

      this.handleUserComboLockService!.constructComboLock(cellCoordinates,droppedImageIndex)  

      this.filledGridTileCounter++


     } else {

      imagesFromUser[droppedImageIndex - 1].style.border = "5px solid #b21616"

      setTimeout(function(){

        imagesFromUser[droppedImageIndex - 1].style.border = "5px solid #000000"

        },
        
        200)


      }
    
      console.log(this.filledGridTileCounter)


      
  }



  resetComboLock(event : any){


    var gridTileContainerElement = this.grid.nativeElement.getElementsByTagName("img")
   
    

    for(let i = 0; i < gridTileContainerElement.length; i++){


      if(gridTileContainerElement[i].getAttribute("src") != null || " "){

          gridTileContainerElement[i].setAttribute("src", " ")

      }


    for(let i = 0; i < this.arrayOfImagesInGrid.length; i++){

       this.arrayOfImagesInGrid[i] = ""
       this.arrayOfImagesInGrid.length = 0
    }

    

  }


  this.filledGridTileCounter = 0

  }


  submitComboLock(event : any){

     if(this.filledGridTileCounter === 4){

      this.handleUserComboLockService.submitComboLock().subscribe(response =>{

        if(response.message === "Combolock Set Successfully"){

          this.router.navigate(["success"])


        }

      })


    


    } else {

      this.grid.nativeElement.style.border = "5px solid #b21616"

      setTimeout(() =>{

        this.grid.nativeElement.style.border = "5px solid #000000"

        },
        
        200)


      }

    }


    }


  

 




