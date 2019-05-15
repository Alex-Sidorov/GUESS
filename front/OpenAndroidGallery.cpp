#include "OpenAndroidGallery.h"

#include <QtAndroidExtras/QAndroidJniObject>
#include <QCoreApplication>
#include <QFile>
#include <QDebug>
#include <QBuffer>
#include <QtAndroid>
#include <QImage>

QString selectedFileName;

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT void JNICALL
Java_org_qtproject_example_QtAndroidGallery_fileSelected(JNIEnv */*env*/,
                                                             jobject /*obj*/,
                                                             jstring results)
{
    qDebug() << results;
    selectedFileName = QAndroidJniObject(results).toString();
    qDebug() << selectedFileName;
}

#ifdef __cplusplus
}
#endif


OpenAndroidGallery::OpenAndroidGallery(QObject *parent) : QObject(parent)
{

}

void OpenAndroidGallery::openGallery()
{
    selectedFileName = "#";
    QAndroidJniObject::callStaticMethod<void>("org/qtproject/example/QtAndroidGallery",
                                              "openAnImage",
                                              "()V");

    while(selectedFileName == "#")
        qApp->processEvents();
    //qDebug()<<selectedFileName;
    if(QFile(selectedFileName).exists())
    {
        qDebug() << "selectedFileName   ;;  " <<  selectedFileName ;
        qDebug() << requestPermissions();
        QImage image;////
        qDebug()<<image.load(selectedFileName);
        qDebug()<<image.size();
        QByteArray arr;
        QBuffer buf(&arr);
        qDebug()<<buf.open(QIODevice::WriteOnly);
        qDebug()<<image.save(&buf, "JPG");
        _data.clear();
        _data = arr;////
        qDebug()<<_data;
        emit sigSendPath(selectedFileName);
    }

}

bool OpenAndroidGallery::requestPermissions()

{

    QtAndroid::PermissionResult r = QtAndroid::checkPermission("android.permission.READ_EXTERNAL_STORAGE");
    if(r == QtAndroid::PermissionResult::Denied) {
        QtAndroid::requestPermissionsSync( QStringList() << "android.permission.READ_EXTERNAL_STORAGE" );
        r = QtAndroid::checkPermission("android.permission.READ_EXTERNAL_STORAGE");
        if(r == QtAndroid::PermissionResult::Denied) {
            return false;
        }
    }
r = QtAndroid::checkPermission("android.permission.WRITE_EXTERNAL_STORAGE");
if(r == QtAndroid::PermissionResult::Denied) {
    QtAndroid::requestPermissionsSync( QStringList() << "android.permission.WRITE_EXTERNAL_STORAGE" );
    r = QtAndroid::checkPermission("android.permission.WRITE_EXTERNAL_STORAGE");
    if(r == QtAndroid::PermissionResult::Denied) {
        return false;
    }
}
return true;}

