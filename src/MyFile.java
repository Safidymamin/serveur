public class MyFile {
    private int id;
    private String name;
    private byte[] data;
    private String fileExtension;

     public void setData(byte[] data) {
         this.data = data;
     }

     public void setFileExtension(String fileExtension) {
         this.fileExtension = fileExtension;
     }
     public void setId(int id) {
         this.id = id;
     }
     public void setName(String name) {
         this.name = name;
     }

     public byte[] getData() {
         return data;
     }
     public String getFileExtension() {
         return fileExtension;
     }
     public int getId() {
         return id;
     }
     public String getName() {
         return name;
     }

     public MyFile(int id,String name,byte[] data,String fileEstension){
        this.setId(id);
        this.setData(data);
        this.setName(name);
        this.setFileExtension(fileEstension);
     }

}
