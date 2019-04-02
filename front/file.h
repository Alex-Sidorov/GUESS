#ifndef FILE_H
#define FILE_H
#include <QObject>
#include <QFile>
#include <QTextStream>
#include <QIODevice>

class FileIO : public QObject
{
    Q_OBJECT

public:
    Q_PROPERTY(QString source
               READ source
               WRITE setSource
               NOTIFY sourceChanged)
    explicit FileIO(QObject *parent = 0);

    //Q_INVOKABLE QString read();
    Q_INVOKABLE bool write(const QString& _data);

    QString source() { return mSource; };


public slots:
    void setSource(const QString& source) { mSource = source; }
    Q_INVOKABLE /*QString*/QByteArray read();
    Q_INVOKABLE QString size_data(){return QString::number(_data.size());}

signals:
    void size_d(QString &v);
    void sourceChanged(const QString& source);
    void error(const QString& msg);

private:
    QString mSource;
    QByteArray _data;
};

#endif // FILE_H
