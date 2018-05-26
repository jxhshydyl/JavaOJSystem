package cn.superman.web.po;

import java.io.Serializable;
import java.util.Date;

public class Announcement  {
    private Integer announcementId;
    private String announcementTitle;
    private String announcementIntroduction;
    private String announcementContent;
    private String announcementCreateManagerId;
    private Date announcementCreateTime;
    private Date announcementPublishTime;
    private Boolean isPublish;

    public Integer getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(Integer announcementId) {
        this.announcementId = announcementId;
    }

    public String getAnnouncementTitle() {
        return announcementTitle;
    }

    public void setAnnouncementTitle(String announcementTitle) {
        this.announcementTitle = announcementTitle;
    }

    public String getAnnouncementIntroduction() {
        return announcementIntroduction;
    }

    public void setAnnouncementIntroduction(String announcementIntroduction) {
        this.announcementIntroduction = announcementIntroduction;
    }

    public String getAnnouncementContent() {
        return announcementContent;
    }

    public void setAnnouncementContent(String announcementContent) {
        this.announcementContent = announcementContent;
    }

    public String getAnnouncementCreateManagerId() {
        return announcementCreateManagerId;
    }

    public void setAnnouncementCreateManagerId(String announcementCreateManagerId) {
        this.announcementCreateManagerId = announcementCreateManagerId;
    }

    public Date getAnnouncementCreateTime() {
        return announcementCreateTime;
    }

    public void setAnnouncementCreateTime(Date announcementCreateTime) {
        this.announcementCreateTime = announcementCreateTime;
    }

    public Date getAnnouncementPublishTime() {
        return announcementPublishTime;
    }

    public void setAnnouncementPublishTime(Date announcementPublishTime) {
        this.announcementPublishTime = announcementPublishTime;
    }

    public Boolean getIsPublish() {
        return isPublish;
    }

    public void setIsPublish(Boolean isPublish) {
        this.isPublish = isPublish;
    }

    @Override
    public String toString() {
        return "Announcement [announcementId=" + announcementId + ", announcementTitle=" + announcementTitle + ", announcementIntroduction="
                + announcementIntroduction + ", announcementContent=" + announcementContent + ", announcementCreateManagerId=" + announcementCreateManagerId
                + ", announcementCreateTime=" + announcementCreateTime + ", announcementPublishTime=" + announcementPublishTime + ", isPublish=" + isPublish
                + "]";
    }
}
