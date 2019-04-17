#include "request.h"
#include <QImage>
#include  <QQmlEngine>
#include  <QQmlComponent>
#include <QQmlContext>
#include <QtAndroid>
#include <QVariant>
#include <QtQuick/QQuickImageProvider>

void Request::send_request(QByteArray data)
{
    qDebug()<<data;
    multiPart = new QHttpMultiPart(QHttpMultiPart::FormDataType);

    QHttpPart imagePart;
    imagePart.setHeader(QNetworkRequest::ContentTypeHeader, QVariant("image/jpg"));
    //imagePart.setHeader(QNetworkRequest::ContentLengthHeader,QVariant(QString::number(data.size())));
    imagePart.setHeader(QNetworkRequest::ContentDispositionHeader, QVariant("form-data; name=\"file\""));

    file = new QFile("file");
    qDebug()<<file->open(QIODevice::ReadWrite);
    qDebug()<<file->write(data);
    qDebug()<<file->seek(0);
    imagePart.setBodyDevice(file);
    //imagePart.setBodyDevice(File(data);
    multiPart->append(imagePart);

    QUrl url("http://hidden-fortress-30624.herokuapp.com/api/v1/recognition");
    QNetworkRequest request(url);
    /*request.setHeader(QNetworkRequest::ContentTypeHeader, QVariant("image/jpg"));
    //imagePart.setHeader(QNetworkRequest::ContentLengthHeader,QVariant(QString::number(data.size())));
    request.setHeader(QNetworkRequest::ContentDispositionHeader, QVariant("form-data; name=\"file\""));
*/
    //connect(&manager,&QNetworkAccessManager::finished,this,&Request::reply_finished);
    connect(&manager,&QNetworkAccessManager::finished,this,&Request::reply_finished);
    //connect(&manager,&QNetworkReply::error,this,&Request::slot_error);
    _reply = manager.post(request, multiPart);

    qDebug()<<_reply->isRunning();

        qDebug()<<request.header(QNetworkRequest::ContentDispositionHeader).toString();
        qDebug()<<request.header(QNetworkRequest::ContentTypeHeader).toString();
    /*qDebug()<<data;
        multiPart = new QHttpMultiPart(QHttpMultiPart::FormDataType);

        QHttpPart imagePart;
        imagePart.setHeader(QNetworkRequest::ContentTypeHeader, QVariant("image/jpg"));
        //imagePart.setHeader(QNetworkRequest::ContentLengthHeader,QVariant(QString::number(data.size())));
        imagePart.setHeader(QNetworkRequest::ContentDispositionHeader, QVariant("form-data; name=\"file\""));

        QUrl imageUrl(data);
        QImage image;
        if(data.mid(0,7) != "file://")
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
            qDebug()<<data;

            /*data = data.mid(6);
            qDebug()<<mSource;
            qDebug()<<image.load(mSource);
            qDebug()<<image.size();*/
       // }

/*

        QByteArray arr;
        file = new QFile("file");
        qDebug()<<file->open(QIODevice::ReadWrite);
        qDebug()<<image.save(file, "JPG");
        //qDebug()<<arr;

        imagePart.setBodyDevice(file);
        //imagePart.setBodyDevice(File(data);
        multiPart->append(imagePart);
        qDebug()<<"rep";

        QUrl url("http://hidden-fortress-30624.herokuapp.com/api/v1/recognition");
        QNetworkRequest request(url);
        //connect(&manager,&QNetworkAccessManager::finished,this,&Request::reply_finished);
        connect(&manager,&QNetworkAccessManager::finished,this,&Request::reply_finished);
        //connect(&manager,&QNetworkReply::error,this,&Request::slot_error);
        _reply = manager.post(request, multiPart);

        qDebug()<<_reply->isRunning();*/
}

void Request::reply_finished()
{
    qDebug()<<"reply";
    QByteArray data = _reply->readAll();
    if(data.isEmpty())
    {
        return;
    }
    qDebug()<<data;
    emit recv_response(data);
    file->close();
    //delete multiPart;
}


void Request::slot_error(QNetworkReply::NetworkError code)
{
    qDebug()<<"error " + QString(code);
}

Request::Request(QObject *parent) : QObject(parent)
{
}

