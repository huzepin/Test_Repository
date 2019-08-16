/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /Volumes/SSD/mmpayface_android/app/app/src/main/aidl/com/tencent/wxpayface/IWxPayFaceAIDL.aidl
 */
package com.tencent.wxpayface;
public interface IWxPayFaceAIDL extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.tencent.wxpayface.IWxPayFaceAIDL
{
private static final String DESCRIPTOR = "com.tencent.wxpayface.IWxPayFaceAIDL";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.tencent.wxpayface.IWxPayFaceAIDL interface,
 * generating a proxy if needed.
 */
public static com.tencent.wxpayface.IWxPayFaceAIDL asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.tencent.wxpayface.IWxPayFaceAIDL))) {
return ((com.tencent.wxpayface.IWxPayFaceAIDL)iin);
}
return new com.tencent.wxpayface.IWxPayFaceAIDL.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_initWxpayface:
{
data.enforceInterface(DESCRIPTOR);
com.tencent.wxpayface.IWxPayFaceCallbackAIDL _arg0;
_arg0 = com.tencent.wxpayface.IWxPayFaceCallbackAIDL.Stub.asInterface(data.readStrongBinder());
this.initWxpayface(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_getWxpayfaceRawdata:
{
data.enforceInterface(DESCRIPTOR);
com.tencent.wxpayface.IWxPayFaceCallbackAIDL _arg0;
_arg0 = com.tencent.wxpayface.IWxPayFaceCallbackAIDL.Stub.asInterface(data.readStrongBinder());
this.getWxpayfaceRawdata(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_getWxpayfaceCode:
{
data.enforceInterface(DESCRIPTOR);
java.util.Map _arg0;
ClassLoader cl = (ClassLoader)this.getClass().getClassLoader();
_arg0 = data.readHashMap(cl);
com.tencent.wxpayface.IWxPayFaceCallbackAIDL _arg1;
_arg1 = com.tencent.wxpayface.IWxPayFaceCallbackAIDL.Stub.asInterface(data.readStrongBinder());
this.getWxpayfaceCode(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_updateWxpayfacePayResult:
{
data.enforceInterface(DESCRIPTOR);
java.util.Map _arg0;
ClassLoader cl = (ClassLoader)this.getClass().getClassLoader();
_arg0 = data.readHashMap(cl);
com.tencent.wxpayface.IWxPayFaceCallbackAIDL _arg1;
_arg1 = com.tencent.wxpayface.IWxPayFaceCallbackAIDL.Stub.asInterface(data.readStrongBinder());
this.updateWxpayfacePayResult(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_reportInfo:
{
data.enforceInterface(DESCRIPTOR);
java.util.Map _arg0;
ClassLoader cl = (ClassLoader)this.getClass().getClassLoader();
_arg0 = data.readHashMap(cl);
com.tencent.wxpayface.IWxPayFaceCallbackAIDL _arg1;
_arg1 = com.tencent.wxpayface.IWxPayFaceCallbackAIDL.Stub.asInterface(data.readStrongBinder());
this.reportInfo(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_reportOrder:
{
data.enforceInterface(DESCRIPTOR);
java.util.Map _arg0;
ClassLoader cl = (ClassLoader)this.getClass().getClassLoader();
_arg0 = data.readHashMap(cl);
com.tencent.wxpayface.IWxPayFaceCallbackAIDL _arg1;
_arg1 = com.tencent.wxpayface.IWxPayFaceCallbackAIDL.Stub.asInterface(data.readStrongBinder());
this.reportOrder(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_releaseWxpayface:
{
data.enforceInterface(DESCRIPTOR);
this.releaseWxpayface();
reply.writeNoException();
return true;
}
case TRANSACTION_getWxpayfaceUserInfo:
{
data.enforceInterface(DESCRIPTOR);
java.util.Map _arg0;
ClassLoader cl = (ClassLoader)this.getClass().getClassLoader();
_arg0 = data.readHashMap(cl);
com.tencent.wxpayface.IWxPayFaceCallbackAIDL _arg1;
_arg1 = com.tencent.wxpayface.IWxPayFaceCallbackAIDL.Stub.asInterface(data.readStrongBinder());
this.getWxpayfaceUserInfo(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_stopWxpayface:
{
data.enforceInterface(DESCRIPTOR);
java.util.Map _arg0;
ClassLoader cl = (ClassLoader)this.getClass().getClassLoader();
_arg0 = data.readHashMap(cl);
com.tencent.wxpayface.IWxPayFaceCallbackAIDL _arg1;
_arg1 = com.tencent.wxpayface.IWxPayFaceCallbackAIDL.Stub.asInterface(data.readStrongBinder());
this.stopWxpayface(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_updateWxpayfaceBannerState:
{
data.enforceInterface(DESCRIPTOR);
java.util.Map _arg0;
ClassLoader cl = (ClassLoader)this.getClass().getClassLoader();
_arg0 = data.readHashMap(cl);
com.tencent.wxpayface.IWxPayFaceCallbackAIDL _arg1;
_arg1 = com.tencent.wxpayface.IWxPayFaceCallbackAIDL.Stub.asInterface(data.readStrongBinder());
this.updateWxpayfaceBannerState(_arg0, _arg1);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.tencent.wxpayface.IWxPayFaceAIDL
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public void initWxpayface(com.tencent.wxpayface.IWxPayFaceCallbackAIDL cb) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((cb!=null))?(cb.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_initWxpayface, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void getWxpayfaceRawdata(com.tencent.wxpayface.IWxPayFaceCallbackAIDL cb) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((cb!=null))?(cb.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_getWxpayfaceRawdata, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void getWxpayfaceCode(java.util.Map info, com.tencent.wxpayface.IWxPayFaceCallbackAIDL cb) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeMap(info);
_data.writeStrongBinder((((cb!=null))?(cb.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_getWxpayfaceCode, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void updateWxpayfacePayResult(java.util.Map info, com.tencent.wxpayface.IWxPayFaceCallbackAIDL cb) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeMap(info);
_data.writeStrongBinder((((cb!=null))?(cb.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_updateWxpayfacePayResult, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void reportInfo(java.util.Map info, com.tencent.wxpayface.IWxPayFaceCallbackAIDL cb) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeMap(info);
_data.writeStrongBinder((((cb!=null))?(cb.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_reportInfo, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void reportOrder(java.util.Map info, com.tencent.wxpayface.IWxPayFaceCallbackAIDL cb) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeMap(info);
_data.writeStrongBinder((((cb!=null))?(cb.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_reportOrder, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void releaseWxpayface() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_releaseWxpayface, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void getWxpayfaceUserInfo(java.util.Map info, com.tencent.wxpayface.IWxPayFaceCallbackAIDL cb) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeMap(info);
_data.writeStrongBinder((((cb!=null))?(cb.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_getWxpayfaceUserInfo, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void stopWxpayface(java.util.Map info, com.tencent.wxpayface.IWxPayFaceCallbackAIDL cb) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeMap(info);
_data.writeStrongBinder((((cb!=null))?(cb.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_stopWxpayface, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void updateWxpayfaceBannerState(java.util.Map info, com.tencent.wxpayface.IWxPayFaceCallbackAIDL cb) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeMap(info);
_data.writeStrongBinder((((cb!=null))?(cb.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_updateWxpayfaceBannerState, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_initWxpayface = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_getWxpayfaceRawdata = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_getWxpayfaceCode = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_updateWxpayfacePayResult = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_reportInfo = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_reportOrder = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_releaseWxpayface = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
static final int TRANSACTION_getWxpayfaceUserInfo = (android.os.IBinder.FIRST_CALL_TRANSACTION + 7);
static final int TRANSACTION_stopWxpayface = (android.os.IBinder.FIRST_CALL_TRANSACTION + 8);
static final int TRANSACTION_updateWxpayfaceBannerState = (android.os.IBinder.FIRST_CALL_TRANSACTION + 9);
}
public void initWxpayface(com.tencent.wxpayface.IWxPayFaceCallbackAIDL cb) throws android.os.RemoteException;
public void getWxpayfaceRawdata(com.tencent.wxpayface.IWxPayFaceCallbackAIDL cb) throws android.os.RemoteException;
public void getWxpayfaceCode(java.util.Map info, com.tencent.wxpayface.IWxPayFaceCallbackAIDL cb) throws android.os.RemoteException;
public void updateWxpayfacePayResult(java.util.Map info, com.tencent.wxpayface.IWxPayFaceCallbackAIDL cb) throws android.os.RemoteException;
public void reportInfo(java.util.Map info, com.tencent.wxpayface.IWxPayFaceCallbackAIDL cb) throws android.os.RemoteException;
public void reportOrder(java.util.Map info, com.tencent.wxpayface.IWxPayFaceCallbackAIDL cb) throws android.os.RemoteException;
public void releaseWxpayface() throws android.os.RemoteException;
public void getWxpayfaceUserInfo(java.util.Map info, com.tencent.wxpayface.IWxPayFaceCallbackAIDL cb) throws android.os.RemoteException;
public void stopWxpayface(java.util.Map info, com.tencent.wxpayface.IWxPayFaceCallbackAIDL cb) throws android.os.RemoteException;
public void updateWxpayfaceBannerState(java.util.Map info, com.tencent.wxpayface.IWxPayFaceCallbackAIDL cb) throws android.os.RemoteException;
}
