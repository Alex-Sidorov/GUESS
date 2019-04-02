#include "file.h"
#include <QImage>
#include  <QQmlEngine>
#include  <QQmlComponent>
#include <QQmlContext>
#include <QBuffer>
#include <QtQuick/QQuickImageProvider>
#include <QDebug>

FileIO::FileIO(QObject *parent) :
    QObject(parent)
{

}

/*QVariant*//*QString*/ QByteArray FileIO::read()
{
    /*if (mSource.isEmpty()){
        emit error("source is empty");
        return QString();
    }

    QFile file(mSource);
    QString fileContent;
    if ( file.open(QIODevice::ReadOnly) ) {
        QString line;
        QTextStream t( &file );
        do {
            line = t.readLine();
            fileContent += line;
         } while (!line.isNull());

        file.close();
    } else {
        emit error("Unable to open the file");
        return QString();
    }
    return fileContent;*/
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
    /*auto im = QString::fromUtf8(arr.toBase64().data());
    buf.close();
    return im;*/
    //auto pix = QVariant(image);
    //return pix;
    _data = arr;
    return _data;
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
