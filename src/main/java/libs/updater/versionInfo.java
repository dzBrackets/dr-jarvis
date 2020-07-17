package libs.updater;

public class versionInfo {
    public String versionName;
    public String versionNumber;
    public boolean isNew;
    public String versionNews;
    public String url;
    public int updateType;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getUpdateType() {
        return updateType;
    }

    public void setUpdateType(int updateType) {
        this.updateType = updateType;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public String getVersionNews() {
        return versionNews;
    }

    public void setVersionNews(String versionNews) {
        this.versionNews = versionNews;
    }
}
