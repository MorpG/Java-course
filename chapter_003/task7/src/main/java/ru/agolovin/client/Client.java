package ru.agolovin.client;

import ru.agolovin.settings.ClientSettings;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class Client {

    //private Socket socket;

    private BufferedReader reader;

    private BufferedReader readerSocket;

    private PrintWriter writer;

    private DataInputStream dataInputStream;

    private DataOutputStream dataOutputStream;

    private String fileName;

    private long fileLenght;

    private File currentDirectory;

    Client(Socket socket) {
        try {
//            this.socket = socket;
            this.reader = new BufferedReader(new InputStreamReader(System.in));
            this.readerSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new PrintWriter(socket.getOutputStream(), true);
            this.dataInputStream = new DataInputStream(socket.getInputStream());
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
            ClientSettings settings = new ClientSettings();
            this.currentDirectory = new File(settings.getStartPath()).getCanonicalFile();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException {
        ClientSettings settings = new ClientSettings();
        Socket socket = new Socket(
                InetAddress.getByName(settings.getServerAddress()), settings.getPort());
        new Client(socket).init();
    }

    void init() {
        try {
            String line;
            if (checkConnection(writer, readerSocket)) {
                System.out.println("Press any key to start");
                reader.readLine();
                writer.println("show menu");
                readSocket(readerSocket);
                do {
                    line = reader.readLine();
                    writer.println(line);
                    if ("4".equals(line)) {
                        readSocket(readerSocket);
                        fileName = readerSocket.readLine();
                        getFile(dataInputStream);
                    }
                    if ("5".equals(line)) {
                        System.out.println("Enter file name to upload");
                        fileName = reader.readLine();
                        writer.println(fileName);
                        sendFile(writer, dataOutputStream);

                    }
                    readSocket(readerSocket);
                } while (!"0".equals(line));
            } else {
                System.out.println("connection failed");
            }


        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void readSocket(BufferedReader readerSocket) {
        String str;
        try {
            str = readerSocket.readLine();
            while (str != null && !str.isEmpty()) {
                System.out.println(str);
                str = readerSocket.readLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private boolean checkConnection(PrintWriter writer, BufferedReader readerSocket) {
        boolean result = false;
        try {
            int i = 0;
            do {
                writer.println("start");
                i++;
                if (readerSocket.readLine().equals("start")) {
                    result = true;
                    System.out.println("Connection to server successful");
                    break;
                }
            } while (i <= 5);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return result;
    }

    private void getFile(DataInputStream dataInputStream) {
        try {
            fileLenght = dataInputStream.readLong();
            if (fileLenght >= 0) {
                try (FileOutputStream fos = new FileOutputStream(currentDirectory + "/" + fileName);) {

                    byte[] buffer = new byte[16 * 1024];
                    int partBuffer = 0;
                    File newFile = new File(currentDirectory + "/" + fileName);
                    while ((partBuffer = dataInputStream.read(buffer)) > 0) {
                        fos.write(buffer, 0, partBuffer);
                        if (newFile.length() == fileLenght) {
                            break;
                        }
                        fos.close();
                    }

                    if (fileLenght == newFile.length()) {
                        System.out.println("Transfer correct");
                    } else {
                        System.out.println("Error transferring");
                    }
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void sendFile(PrintWriter writer, DataOutputStream dataOutputStream) {
        try {
            System.out.println("Enter file name to upload: ");
            String newFileName = reader.readLine();
            File[] element;
            if ((element = currentDirectory.listFiles()) != null) {
                for (File file : element) {
                    if (file.getName().equals(newFileName) && file.isFile()) {
                        writer.println(newFileName);
                        File sendFile = new File(currentDirectory + "" + newFileName);
                        if (sendFile.isFile()) {
                            fileLenght = sendFile.length();
                            writer.println(String.valueOf(fileLenght));
                            try {
                                FileInputStream fis = new FileInputStream(sendFile);
                                byte[] buffer = new byte[16 * 1024];
                                int partBuffer = 0;
                                while ((partBuffer = fis.read(buffer)) >= 0) {
                                    dataOutputStream.write(buffer, 0, partBuffer);
                                }
                            } catch (IOException ioe) {
                                ioe.printStackTrace();
                            }
                        }
                    }
                }

            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
