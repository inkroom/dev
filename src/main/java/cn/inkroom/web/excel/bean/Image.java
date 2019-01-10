package cn.inkroom.web.excel.bean;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/11/28
 * @Time 11:24
 * @Descorption
 */
public class Image {

    private String thumbURL;
    private String middleURL;
    private String hoverURL;
    private String objURL;

    public String getThumbURL() {
        return thumbURL;
    }

    public void setThumbURL(String thumbURL) {
        this.thumbURL = thumbURL;
    }

    public String getMiddleURL() {
        return middleURL;
    }

    public void setMiddleURL(String middleURL) {
        this.middleURL = middleURL;
    }

    public String getHoverURL() {
        return hoverURL;
    }

    public void setHoverURL(String hoverURL) {
        this.hoverURL = hoverURL;
    }

    public String getObjURL() {
        return objURL;
    }

    public void setObjURL(String objURL) {
        this.objURL = objURL;
    }

    @Override
    public String toString() {
        return "Image{" +
                "thumbURL='" + thumbURL + '\'' +
                ", middleURL='" + middleURL + '\'' +
                ", hoverURL='" + hoverURL + '\'' +
                ", objURL='" + objURL + '\'' +
                '}';
    }
}
