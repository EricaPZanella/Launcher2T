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
    ArrayList<AppInfo>  appInfoArrayListResult ;
    List<ApplicationInfo> applicationInfoList;

    LaucherControler ( Context  context){
        mContext = context;
        applicationInfoList= mContext.getPackageManager().getInstalledApplications(0);
        appInfoArrayList = new ArrayList<AppInfo>();
    }


    public ArrayList<AppInfo> loadAppInf(final String key){
        appInfoArrayList = new ArrayList<AppInfo>();
        appInfoArrayListResult= new ArrayList<AppInfo>();

        // LoadApps using Intent Query and filter intent category Launcher.
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> pkgAppsList = mContext.getPackageManager().queryIntentActivities( mainIntent, 0);
        Iterator<ResolveInfo> resolveInfoIterator = pkgAppsList.iterator();

        //Create arrayListAppInfo, (Easily optimized, but not done)
        while(resolveInfoIterator.hasNext()){
            ResolveInfo resolveInfo = resolveInfoIterator.next();
            appInfoArrayList.add(new AppInfo(resolveInfo.activityInfo.applicationInfo,mContext.getPackageManager()));
        }

        //Order by itens
        ComparatorAppInfo comparator = new ComparatorAppInfo();
        //Lamda Expression (>1.8)  see build.gralde  has pragma compile
        Collection<AppInfo>  appInfos = appInfoArrayList.stream().filter((d) -> d.appname.toLowerCase().contains (key.toLowerCase().trim())).collect(Collectors.toList());
        appInfoArrayList = new ArrayList<>(appInfos);
        Collections.sort(appInfoArrayList, comparator);
        //impondo ordem fixa a aplicativos.
        String [] listaPacoteApp={"com.google.android.youtube",}; //Array com pacotes 
        int i=0;
        for ( AppInfo appinfo:appInfoArrayList) {
                for ( i=0; i<listaPacoteApp.length ; i++) {
                    if (appinfo.pname.equals( listaPacoteApp[i]) ) {
                        appInfoArrayListResult.add(appinfo);
                    }
                }

        }
        return  appInfoArrayListResult;
    }

}
