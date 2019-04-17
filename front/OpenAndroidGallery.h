#ifndef OPENANDROIDGALLERY_H
#define OPENANDROIDGALLERY_H

#include <QObject>
#include <QByteArray>

class OpenAndroidGallery : public QObject
{
    Q_OBJECT

 //   Q_PROPERTY(void name READ name WRITE setName NOTIFY nameChanged)

public:
    explicit OpenAndroidGallery(QObject *parent = nullptr);
    Q_INVOKABLE bool requestPermissions();

public slots:
    Q_INVOKABLE QByteArray get_file(){return _data;}
    Q_INVOKABLE QString get_size(){return QString::number(_data.size());}
    void openGallery();

signals:

    void sigSendPath(QString path);

private:
    QByteArray _data;

};


#endif // OPENANDROIDGALLERY_H
