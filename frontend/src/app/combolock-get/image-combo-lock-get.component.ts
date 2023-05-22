import { AfterViewInit, Component, ElementRef, OnInit, Renderer2, ViewChild, } from '@angular/core';
import { RetrieveImageChunksService } from '../services/retrieve-image-chunks.service';
import { IImage } from '../IImage';
import { Observable } from 'rxjs';
import { NavigationEnd, Router } from '@angular/router';
import { HandleUserComboLockService } from '../services/handle-user-combo-lock.service';
import { filter } from 'rxjs/operators'

@Component({
  selector: 'app-image-combo-lock-get',
  templateUrl: './image-combo-lock-get.component.html',
  styleUrls: ['./image-combo-lock-get.component.css']
})
export class ImageComboLockGetComponent {

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
                    this.router.navigate(["user/login"]);
                    alert("Please Log in Again")
                  }
                })


              }

     
     images!: Observable<IImage[]>
     arrayOfImagesInGrid: Array<string> = []
     filledGridTileCounter:number = 0
     userComboLockFromStorage! : string




  ngOnInit() {

    this.images = this.retrieveImageChunksService.getImageChunks()
    this.handleUserComboLockService.retrieveComboLock().subscribe(response => this.userComboLockFromStorage = response.data)
    
    
    


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

  


    

    if(!this.arrayOfImagesInGrid.includes(droppedImageSource) && (!targetClassName.includes("grid"))) {

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

    if(this.filledGridTileCounter != 4){

      this.grid.nativeElement.style.border = "5px solid #b21616"

      setTimeout(() =>{

        this.grid.nativeElement.style.border = "5px solid #000000"

        },
        
        200)

    
    } 


    var stringCombolock = this.userComboLockFromStorage
    var userCombolockLive = JSON.stringify(Object.fromEntries(this.handleUserComboLockService.comboLock))
    var userCombolockLiveFinal = userCombolockLive.replace(/\D/g, "");
    
    if(stringCombolock === userCombolockLiveFinal){

      setTimeout(() =>{

        this.grid.nativeElement.style.border = "5px solid #2d943e"
      },
      
      100)

      this.grid.nativeElement.style.border = "5px solid #000000"


      setTimeout(() =>{

          this.router.navigate(["successfull_logIn"])
        },
        
        1000)



    }else{

  
      this.grid.nativeElement.style.border = "5px solid #b21616"

      setTimeout(() =>{

        this.grid.nativeElement.style.border = "5px solid #000000"

        },
        
        500)

    }
  }

 







}