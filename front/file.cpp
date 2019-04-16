#include "file.h"
#include <QImage>
#include  <QQmlEngine>
#include  <QQmlComponent>
#include <QQmlContext>
#include <QBuffer>
#include <QtQuick/QQuickImageProvider>
#include <QDebug>
#include <QtAndroid>

FileIO::FileIO(QObject *parent) :
    QObject(parent)
{

}

QString FileIO::answer_for_victorine(QString data)
{
    data.resize(data.size()-1);
    auto answers = data.split(' ');
    if(!animals.count(answers[0]))
    {
        answers.clear();
    }
    while(answers.count() != 4)
    {
        auto temp = animals[random()%animals.count()];
        if(answers.count(temp) == 0)
        {
            answers.push_back(temp);
        }
    }

    std::random_shuffle(answers.begin(),answers.end());
    QString result;
    foreach (auto item, answers)
    {
        result += item + ' ';
    }
    return result;
}

/*QVariant*//*QString*/ QByteArray FileIO::read()
{

    QUrl imageUrl(mSource);
    QImage image;
    if(mSource.mid(0,7) != "file://")
    {
        QQmlEngine* engine = QQmlEngine::contextForObject(this)->engine();
        QQmlImageProviderBase* imageProviderBase = engine->imageProvider(imageUrl.host());
        QQuickImageProvider* imageProvider = static_cast<QQuickImageProvider*>(imageProviderBase);

        QSize imageSize;
        QString imageId = imageUrl.path().remove(0,1);
        image = imageProvider->requestImage(imageId, &imageSize, imageSize);
    }
    else
    {
        qDebug()<<mSource;
        qDebug()<<this->requestPermissions();
        mSource = mSource.mid(6);
        qDebug()<<mSource;
        qDebug()<<image.load(mSource);
        qDebug()<<image.size();
    }



    QByteArray arr;
    QBuffer buf(&arr);
    qDebug()<<buf.open(QIODevice::WriteOnly);
    qDebug()<<image.save(&buf, "JPG");
    _data.clear();
    _data = arr;//
    //qDebug()<<_data;
    return _data;
    /*qDebug()<<mSource;
    QUrl imageUrl(mSource);

    QQmlEngine* engine = QQmlEngine::contextForObject(this)->engine();
    QQmlImageProviderBase* imageProviderBase = engine->imageProvider(imageUrl.host());
    QQuickImageProvider* imageProvider = static_cast<QQuickImageProvider*>(imageProviderBase);

    QSize imageSize;
    QString imageId = imageUrl.path().remove(0,1);
    QImage image = imageProvider->requestImage(imageId, &imageSize, imageSize);

    QByteArray arr;
    QBuffer buf(&arr);
    buf.open(QIODevice::WriteOnly);
    image.save(&buf, "JPG");

    _data = arr;
    return _data;*/
}

bool FileIO::write(const QString& data)
{
    if (mSource.isEmpty())
        return false;

    QFile file(mSource);
    if (!file.open(QFile::WriteOnly | QFile::Truncate))
        return false;

    QTextStream out(&file);
    out << data;

    file.close();

    return true;
}

bool FileIO::requestPermissions()
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
    return true;
}
