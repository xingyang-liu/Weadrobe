package com.xiecc.Weadrobe.modules.about.domain;

import com.google.gson.annotations.SerializedName;

public class VersionAPI {

    @SerializedName("name") public String name;
    @SerializedName("version") public String version;
    @SerializedName("changelog") public String changelog;
    @SerializedName("versionShort") public String versionShort;
    @SerializedName("build") public String build;
    @SerializedName("update_url") public String updateUrl;


    public static class BinaryEntity {
        @SerializedName("fsize") public int fsize;
    }
}
