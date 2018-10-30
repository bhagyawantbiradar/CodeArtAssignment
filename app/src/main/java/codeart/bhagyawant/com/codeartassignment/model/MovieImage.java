
package codeart.bhagyawant.com.codeartassignment.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieImage {
    @SerializedName("backdrops")
    @Expose
    private List<Backdrop> backdrops = null;

    public List<Backdrop> getBackdrops() {
        return backdrops;
    }

    public void setBackdrops(List<Backdrop> backdrops) {
        this.backdrops = backdrops;
    }

}
