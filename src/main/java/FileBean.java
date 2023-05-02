import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;


@RequestScoped
@Named(value = "fileBean")
public class FileBean implements Serializable {

    private Part uploadedFile;

    public Part getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(Part uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public String validate() {

        if (uploadedFile.getContentType().contentEquals("text/csv")) {
            try {
                String appPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("");
                String savePath = appPath + "uploads";

                File fileSaveDir = new File(savePath);

                if (!fileSaveDir.exists()) fileSaveDir.mkdir();

                File f = new File(savePath + File.separator + "kd.csv");
                if (f.exists()) f.delete();

                uploadedFile.write(savePath + File.separator + "kd.csv");
            } catch (IOException e) {
                e.printStackTrace();
            }

            return "analysis";

        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falsches Dateiformat!", null);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("form:fileUpload", message);
            return "";
        }
    }
}
