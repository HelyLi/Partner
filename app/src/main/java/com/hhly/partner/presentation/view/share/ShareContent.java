package com.hhly.partner.presentation.view.share;

import android.os.Parcel;
import android.os.Parcelable;


public class ShareContent implements Parcelable {

    private String webUrl;//网页打开链接
    private String webTitle;//toolbar
    private String appName;
    private String title;
    private String description;
    private String content;
    private String image;
    private String link;//分享链接
    private String inviteCode;
    private boolean showShare = true;

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getWebTitle() {
        return webTitle;
    }

    public void setWebTitle(String webTitle) {
        this.webTitle = webTitle;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public boolean isShowShare() {
        return showShare;
    }

    public void setShowShare(boolean showShare) {
        this.showShare = showShare;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.webUrl);
        dest.writeString(this.webTitle);
        dest.writeString(this.appName);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.content);
        dest.writeString(this.image);
        dest.writeString(this.link);
        dest.writeString(this.inviteCode);
        dest.writeByte(this.showShare ? (byte) 1 : (byte) 0);
    }

    public ShareContent() {
    }

    protected ShareContent(Parcel in) {
        this.webUrl = in.readString();
        this.webTitle = in.readString();
        this.appName = in.readString();
        this.title = in.readString();
        this.description = in.readString();
        this.content = in.readString();
        this.image = in.readString();
        this.link = in.readString();
        this.inviteCode = in.readString();
        this.showShare = in.readByte() != 0;
    }

    public static final Creator<ShareContent> CREATOR = new Creator<ShareContent>() {
        @Override
        public ShareContent createFromParcel(Parcel source) {
            return new ShareContent(source);
        }

        @Override
        public ShareContent[] newArray(int size) {
            return new ShareContent[size];
        }
    };
}
