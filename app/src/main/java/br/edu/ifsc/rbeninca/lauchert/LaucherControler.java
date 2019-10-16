package br.edu.ifsc.rbeninca.lauchert;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.ResolveInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class LaucherControler {
    Context mContext ;
    ArrayList<AppInfo>  appInfoArrayList ;
    List<ApplicationInfo> applicationInfoList;

    LaucherControler ( Context  context){
        mContext = context;
        applicationInfoList= mContext.getPackageManager().getInstalledApplications(0);
        appInfoArrayList = new ArrayList<AppInfo>();
    }


    public ArrayList<AppInfo> loadAppInf(final String key){
        appInfoArrayList = new ArrayList<AppInfo>();

        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> pkgAppsList = mContext.getPackageManager().queryIntentActivities( mainIntent, 0);
        Iterator<ResolveInfo> resolveInfoIterator = pkgAppsList.iterator();
        while(resolveInfoIterator.hasNext()){
            ResolveInfo resolveInfo = resolveInfoIterator.next();
            appInfoArrayList.add(new AppInfo(resolveInfo.activityInfo.applicationInfo,mContext.getPackageManager()));
        }

        ComparatorAppInfo comparator = new ComparatorAppInfo();
        Collection<AppInfo>  appInfos = appInfoArrayList.stream().filter((d) -> d.appname.toLowerCase().contains (key.toLowerCase().trim())).collect(Collectors.toList());
        appInfoArrayList = new ArrayList<>(appInfos);
        Collections.sort(appInfoArrayList, comparator);

        return  appInfoArrayList;
    }

}