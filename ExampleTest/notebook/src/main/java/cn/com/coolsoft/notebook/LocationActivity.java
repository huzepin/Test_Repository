package cn.com.coolsoft.notebook;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import cn.com.coolsoft.notebook.utils.PermissionsUtils;

/**
 * @author admin
 * @time 2019/9/19 10
 */
public class LocationActivity extends AppCompatActivity {

    private static Context mContext;
    private static LocationManager m_locationManager;
    private static String m_provider;

    String[] permissions = new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_activity);
        PermissionsUtils.getInstance().chekPermissions(this, permissions, permissionsResult);
        //29.6699326612 115.9456079181
       // 29.2976254618,116.3335866961
      //  String addr = getAddress(29.2976254618,116.3335866961);
      //  startLocation(this);
    }

    //创建监听权限的接口对象
    PermissionsUtils.IPermissionsResult permissionsResult = new PermissionsUtils.IPermissionsResult() {
        @Override
        public void passPermissons() {
            //  Toast.makeText(LocationActivity.this, "权限通过，可以做其他事情!", Toast.LENGTH_SHORT).show();
            startLocation(LocationActivity.this);
        }

        @Override
        public void forbitPermissons() {
            //finish();
            Toast.makeText(LocationActivity.this, getString(R.string.no_permission), Toast.LENGTH_SHORT).show();
        }
    };

    private static LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            updateToNewLocation(location);
            if (location != null) {
                String string = "纬度为：" + location.getLatitude() + ",经度为：" + location.getLongitude();
                Log.i("LocationActivity", "string" + string);
                getAddress(location);
              //  onActivityStoped();
            }
        }

        @Override
        public void onProviderDisabled(String arg0) {
            updateToNewLocation(null);

        }

        @Override
        public void onProviderEnabled(String arg0) {
            Log.i("LocationActivity", "string" + arg0);
        }

        @Override
        public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
            Log.i("LocationActivity", "string" + arg0);
        }
    };

    public static void startLocation(Context context) {
        mContext = context;
        //获取定位服务
        m_locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        //获取当前可用的位置控制器
        List<String> list = m_locationManager.getProviders(true);
        if (list.contains(LocationManager.GPS_PROVIDER)) {
            //是否为GPS位置控制器
            m_provider = LocationManager.GPS_PROVIDER;//NETWORK_PROVIDER GPS_PROVIDER
            Log.i("LocationActivity", "is GPS");
        } else if (list.contains(LocationManager.NETWORK_PROVIDER)) {
            //是否为网络位置控制器
            m_provider = LocationManager.NETWORK_PROVIDER;
            Log.i("LocationActivity", "is network");
        } else if (list.contains(LocationManager.PASSIVE_PROVIDER)){
            m_provider = "passive";
        }

        if (m_provider != null) {
            if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
               return;
            }



            //创建一个criteria对象
            Criteria criteria = new Criteria();
            criteria.setAccuracy(Criteria.ACCURACY_COARSE);
            //设置不需要获取海拔方向数据
            criteria.setAltitudeRequired(false);
            criteria.setBearingRequired(false);
            //设置允许产生资费
            criteria.setCostAllowed(true);
            //要求低耗电
            criteria.setPowerRequirement(Criteria.POWER_LOW);
            String provider = m_locationManager.getBestProvider(criteria, true);
            Log.i("Tobin", "Location Provider is "+ provider);
            Location location = m_locationManager.getLastKnownLocation(provider);



          //  Location location = m_locationManager.getLastKnownLocation(m_provider);
            if(location!=null){
                getAddress(location);
//直接获取
            }else{
//没有需要加监听等待获取
              //  m_locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 0, locationListener);
                m_locationManager.requestLocationUpdates(m_provider, 2000, 0, locationListener);

              //  m_locationManager.requestLocationUpdates(m_provider, 3000, 1, locationListener);
            }
        }
    }


    private static Location updateToNewLocation(Location location) {

        String latLongString;
        double lat = 0;
        double lng=0;

        if (location != null) {
            lat = location.getLatitude();
            lng = location.getLongitude();
            latLongString = "纬度:" + lat + "\n经度:" + lng;
            System.out.println("经度："+lng+"纬度："+lat);
        } else {
            latLongString = "无法获取地理信息，请稍后...";
        }
        if(lat!=0){
            System.out.println("--------反馈信息----------"+ String.valueOf(lat));
        }

        Toast.makeText(mContext, latLongString, Toast.LENGTH_SHORT).show();
  //纬度:22.586702  113.85369
        //经度:113.85369
        return location;

    }


    public static void onActivityStoped(){
        if(locationListener != null){
            m_locationManager.removeUpdates(locationListener);
            locationListener = null;
        }
        Log.i("LocationActivity","onActivityStoped");
    }

    public String getAddress(double latitude, double longitude) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
           //Address[addressLines=[0:"江西省九江市都昌县阳峰乡胡家山"],
            // feature=阳峰乡胡家山,admin=江西省,sub-admin=null,locality=九江市,thoroughfare=null,postalCode=null,
            // countryCode=CN,countryName=中国,hasLatitude=true,latitude=29.297618,hasLongitude=true,longitude=116.333577,
            // phone=null,url=null,extras=Bundle[mParcelledData.dataSize=92]]
            if (addresses.size() > 0) {
                Address address = addresses.get(0);
                String data = address.toString();
                int startCity = data.indexOf("1:\"") + "1:\"".length();
                int endCity = data.indexOf("\"", startCity);
                String city = data.substring(startCity, endCity);
                int startPlace = data.indexOf("feature=") + "feature=".length();
                int endplace = data.indexOf(",", startPlace);
                String place = data.substring(startPlace, endplace);
                return city + place;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "获取失败";
    }

    private static String getAddress(Location location){
        //用来接收位置的详细信息
        List<Address> result = null;
        String addressLine = "";
        try {
            if (location != null) {
                Geocoder gc = new Geocoder(mContext, Locale.getDefault());
                result = gc.getFromLocation(location.getLatitude(),
                        location.getLongitude(), 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(result != null && result.get(0) != null){
            //这块获取到的是个数组我们取一个就好 下面是具体的方法查查API就能知道自己要什么
            //result.get(0).getCountryName()

            Log.i("LocationActivity",addressLine);
            Toast.makeText(mContext,result.get(0).toString(),Toast.LENGTH_LONG).show();
        }

        return addressLine;
    }
}
