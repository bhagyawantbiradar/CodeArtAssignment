
package codeart.bhagyawant.com.codeartassignment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Backdrop {
    @SerializedName("file_path")
    @Expose
    private String filePath;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "Backdrop{" +
                ", filePath='" + filePath + '\'' +
                '}';
    }
}
