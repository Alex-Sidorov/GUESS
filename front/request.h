#ifndef REQUEST_H
#define REQUEST_H

#include <QObject>
#include <QByteArray>
#include <QNetworkAccessManager>
#include <QHttpMultiPart>
#include <QNetworkReply>
#include <QDebug>
#include <QFile>
#include <QBuffer>
class Request : public QObject
{
    Q_OBJECT
public:
    explicit Request(QObject *parent = nullptr);
    virtual ~Request(){}

signals:
    void recv_response(const QString response);

public slots:
    Q_INVOKABLE void send_request(QByteArray data);
    Q_INVOKABLE void reply_finished();
    void slot_error(QNetworkReply::NetworkError code);

private:
    QFile *file;
    QNetworkReply *_reply;
    QHttpMultiPart *multiPart;
    QNetworkAccessManager manager;
};

#endif // REQUEST_H
