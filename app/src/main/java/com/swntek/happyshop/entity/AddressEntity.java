package com.swntek.happyshop.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者：wgyhello on 15/10/21 13:18
 * 邮箱：429883793@qq.com
 * 地址实体
 */
public class AddressEntity implements Parcelable {






    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString("ww");
    }

    public static final Creator<AddressEntity> CREATOR = new Creator<AddressEntity>() {
        @Override
        public AddressEntity createFromParcel(Parcel in) {
            return new AddressEntity(in);
        }

        @Override
        public AddressEntity[] newArray(int size) {
            return new AddressEntity[size];
        }
    };



    protected AddressEntity(Parcel in) {
//        xx = in.readInt();

    }





}
