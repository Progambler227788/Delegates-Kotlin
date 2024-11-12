interface Downloader{
    fun download()
}

interface Uploader{
    fun upload()
}
class VideoDownload(val fileName:String) : Downloader {
    override fun download() {

        println(fileName + " Downloaded")
    }

}
class VideoUploader(val fileName:String) : Uploader{
    override fun upload() {
        println(fileName + " Uploaded")
    }
}
class FileHandler(val downloader : Downloader, val uploader : Uploader) : Downloader by downloader, Uploader by uploader{
//    override fun download() {
//        downloader.download()
//    }
//
//    override fun upload() {
//       uploader.upload()
//    }
}

fun main (){
    val downloader = VideoDownload("A")
    val uploader = VideoUploader("B")
    val fileHandler = FileHandler(downloader,uploader)
    fileHandler.upload()
    fileHandler.download()

    //
//    When you use by, you’re essentially saying:
//    "I want someone else (a delegate) to handle this property for me."
    // by is for delegation: It lets another object manage a property for you.


}