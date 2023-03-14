package com.adarshpanig.placementapp;

public class Upload {
    private String mName;
    private String mDesc;
    private String mImageUrl;
    public Upload()
    {

    }
    public Upload(String name, String desc , String imageUrl)
    {
        if(name.trim().equals(""))
        {
            name="No Name";
        }
        mName=name;
        mDesc=desc;
        mImageUrl=imageUrl;
    }

    public String getName()
    {
        return mName;
    }
    public void setName(String name)
    {
        mName=name;
    }
    public String getDesc()
    {
        return mDesc;
    }
    public void setDesc(String desc)
    {
        mDesc=desc;
    }
    public String getImageUrl()
    {
        return mImageUrl;
    }
    public void setImageUrl(String imageUrl)
    {
        mImageUrl=imageUrl;
    }
}
