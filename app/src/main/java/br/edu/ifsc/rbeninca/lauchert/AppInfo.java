package br.edu.ifsc.rbeninca.lauchert;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

public class AppInfo {

    public String appname = "";
    public String pname = "";
    public String versionName = "";
    public String datadir;
    public int versionCode = 0;
    public Drawable icon;

    public AppInfo(PackageInfo packageInfo , PackageManager pm) {
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;

            this.versionName= packageInfo.versionName;
            this.versionCode= packageInfo.versionCode;

            this.appname=applicationInfo.loadLabel(pm).toString();
            this.pname=applicationInfo.packageName;
            this.datadir=applicationInfo.dataDir;
            this.icon=applicationInfo.loadIcon(pm);

    }

    public AppInfo(ApplicationInfo applicationInfo , PackageManager pm) {
        PackageInfo packageInfo=new PackageInfo();
        try{
            packageInfo= pm.getPackageInfo(applicationInfo.packageName,0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        this.versionName=packageInfo.versionName;
        this.versionCode=packageInfo.versionCode;

        this.appname=applicationInfo.loadLabel(pm).toString();
        this.pname=applicationInfo.packageName;
        this.datadir=applicationInfo.dataDir;
        this.icon=applicationInfo.loadIcon(pm);

    }


}
