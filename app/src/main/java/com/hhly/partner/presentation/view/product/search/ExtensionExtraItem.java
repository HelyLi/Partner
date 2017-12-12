package com.hhly.partner.presentation.view.product.search;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 推广搜索时传递extra
 * Created by dell on 2017/5/4.
 */

public class ExtensionExtraItem implements Parcelable {
    private int modelId;
    private int promotionPosition;

    public ExtensionExtraItem(int modelId, int promotionPosition) {
        this.modelId = modelId;
        this.promotionPosition = promotionPosition;
    }

    public ExtensionExtraItem(Parcel in) {
        this.modelId = in.readInt();
        this.promotionPosition = in.readInt();
    }


    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public int getPromotionPosition() {
        return promotionPosition;
    }

    public void setPromotionPosition(int promotionPosition) {
        this.promotionPosition = promotionPosition;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(modelId);
        dest.writeInt(promotionPosition);
    }

    public static final Parcelable.Creator<ExtensionExtraItem> CREATOR = new Parcelable.Creator<ExtensionExtraItem>() {
        public ExtensionExtraItem createFromParcel(Parcel in) {
            return new ExtensionExtraItem(in);
        }

        public ExtensionExtraItem[] newArray(int size) {
            return new ExtensionExtraItem[size];
        }
    };


}
