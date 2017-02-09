/*___Generated_by_IDEA___*/

/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /Users/bmgustaf/IdeaProjects/ThreadingSamples/src/com/intercall/ThreadingSamples/ISyncronous.aidl
 */
package com.intercall.ThreadingSamples;
public interface ISyncronous extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.intercall.ThreadingSamples.ISyncronous
{
private static final java.lang.String DESCRIPTOR = "com.intercall.ThreadingSamples.ISyncronous";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.intercall.ThreadingSamples.ISyncronous interface,
 * generating a proxy if needed.
 */
public static com.intercall.ThreadingSamples.ISyncronous asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.intercall.ThreadingSamples.ISyncronous))) {
return ((com.intercall.ThreadingSamples.ISyncronous)iin);
}
return new com.intercall.ThreadingSamples.ISyncronous.Stub.Proxy(obj);
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
case TRANSACTION_getThreadNameFast:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _result = this.getThreadNameFast();
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_getThreadNameBlocking:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _result = this.getThreadNameBlocking();
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_getThreadNameUnblock:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _result = this.getThreadNameUnblock();
reply.writeNoException();
reply.writeString(_result);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.intercall.ThreadingSamples.ISyncronous
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
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public java.lang.String getThreadNameFast() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getThreadNameFast, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
//This method is supposed to run async because of the oneway keyword and the provided callback
//oneway void getThreadNameSlow(IAsynchronousCallback callback);

@Override public java.lang.String getThreadNameBlocking() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getThreadNameBlocking, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.lang.String getThreadNameUnblock() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getThreadNameUnblock, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_getThreadNameFast = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_getThreadNameBlocking = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_getThreadNameUnblock = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
}
public java.lang.String getThreadNameFast() throws android.os.RemoteException;
//This method is supposed to run async because of the oneway keyword and the provided callback
//oneway void getThreadNameSlow(IAsynchronousCallback callback);

public java.lang.String getThreadNameBlocking() throws android.os.RemoteException;
public java.lang.String getThreadNameUnblock() throws android.os.RemoteException;
}
