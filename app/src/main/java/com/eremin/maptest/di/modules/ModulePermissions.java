package com.eremin.maptest.di.modules;


public class ModulePermissions {
    /*private List<String> permissions = null;
    public ModulePermissions(){
        //App.getComponent().injectPermissions(this);
    }
    public boolean checkPermissionLocation(){
        boolean statusPermission = false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (
                    ContextCompat.checkSelfPermission(ContextModel.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(ContextModel.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
            ) {
                statusPermission = false;
                permissions = new ArrayList<>();
                permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
                permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            }else {
                statusPermission = true;
            }
        }
        permission(ConstantManager.REQUEST_CODE_LOCATION);
        return statusPermission;
    }
    private void permission(int requestCode){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (permissions != null) {
                String[] permissionArray = new String[permissions.size()];
                permissions.toArray(permissionArray);
                ActivityCompat.requestPermissions(ContextModel.getActivity(), permissionArray, requestCode);
            }
        }
    }*/
}
