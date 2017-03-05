package ru.agolovin.client;

import ru.agolovin.settings.ClientSettings;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class Client {

    /**
     * BufferSize.
     */
    private final int bufferSize = 16;
    /**
     * BufferSize.
     */
    private final int bufferSizeN = 1024;
    /**
     * Buffered reader console.
     */
    private BufferedReader reader;
    /**
     * Buffered reader socketInputStream.
     */
    private BufferedReader readerSocket;
    /**
     * PrintWriter socket.
     */
    private PrintWriter writer;
    /**
     * DataInputStream socket.
     */
    private DataInputStream dataInputStream;
    /**
     * DataOutPutStream socket.
     */
    private DataOutputStream dataOutputStream;
    /**
     * FileName.
     */
    private String fileName;
    /**
     * FileLength.
     */
    private long fileLength;
    /**
     * Current Directory.
     */
    private File currentDirectory;


    /**
     * Constructor.
     *
     * @param socket Socket
     */
    Client(Socket socket) {
        try {
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

    /**
     * main method.
     *
     * @param args String
     * @throws IOException exception
     */
    public static void main(String[] args) throws IOException {
        ClientSettings settings = new ClientSettings();
        Socket socket = new Socket(
                InetAddress.getByName(settings.getServerAddress()), settings.getPort());
        new Client(socket).init();
    }

    /**
     * Initialization.
     */
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
                        fileName = reader.readLine();
                        writer.println(fileName);
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

    /**
     * Read data from socket.
     *
     * @param readerSocket BufferedReader
     */
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

    /**
     * Check connection to server.
     *
     * @param writer       PrintWriter
     * @param readerSocket BufferedReader
     * @return result boolean
     */
    private boolean checkConnection(PrintWriter writer, BufferedReader readerSocket) {
        boolean result = false;
        try {
            int i = 0;
            final int maxTry = 5;
            do {
                writer.println("start");
                i++;
                if (readerSocket.readLine().equals("start")) {
                    result = true;
                    System.out.println("Connection to server successful");
                    break;
                }
            } while (i <= maxTry);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return result;
    }

    /**
     * Get file from server.
     *
     * @param dataInputStream DataInputStream.
     */
    private void getFile(DataInputStream dataInputStream) {
        try {
            String check = readerSocket.readLine();
            if (check.equals("correct")) {
                fileLength = Long.parseLong(readerSocket.readLine());
                if (fileLength > 0) {
                    String filePath = String.format("%s/download", currentDirectory);
                    String filePath2 = String.format("%s/download/%s", currentDirectory, fileName);
                    File newFile = new File(filePath);
                    if (!newFile.isDirectory()) {
                        if (!newFile.mkdir()) {
                            System.out.println("Error downloading");
                        }
                    }
                    File newFile2 = new File(filePath2);
                    try (FileOutputStream fos = new FileOutputStream(newFile2)) {
                        byte[] buffer = new byte[bufferSize * bufferSizeN];
                        int partBuffer = 0;
                        long tempLength = fileLength;
                        while (tempLength > 0) {
                            partBuffer = dataInputStream.read(buffer);
                            fos.write(buffer, 0, partBuffer);
                            fos.flush();
                            tempLength -= partBuffer;
                        }

                        fos.close();
                        String message;
                        if (fileLength == newFile2.length()) {
                            message = "Transfer correct";
                            System.out.println(message);
                            System.out.println(String.format("File download to: %s", newFile.getAbsoluteFile()));
                            writer.println(message);
                        } else {
                            message = "Error transferring";
                            System.out.println(message);
                            writer.println(message);
                        }
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


    /**
     * Send File to server.
     *
     * @param writer           PrintWriter
     * @param dataOutputStream DataOutPutStream.
     */
    private void sendFile(PrintWriter writer, DataOutputStream dataOutputStream) {
        try {
            File[] element = currentDirectory.listFiles();
            boolean flag = false;
            if (element != null) {
                for (File file : element) {
                    if (file.getName().equals(fileName)) {
                        File fileClient = new File(currentDirectory + "/" + fileName);
                        if (fileClient.exists() && fileClient.isFile()) {
                            writer.println("correct");
                            writer.println(file.length());
                            flag = true;
                            try (FileInputStream fis = new FileInputStream(fileClient)) {
                                int partBuf;
                                byte[] buffer = new byte[bufferSize * bufferSizeN];
                                while ((partBuf = fis.read(buffer)) != -1) {
                                    dataOutputStream.write(buffer, 0, partBuf);
                                    dataOutputStream.flush();
                                }
                                fis.close();
                            }
                        } else {
                            writer.println("File error");
                        }
                    }
                }
                if (!flag) {
                    writer.println("File not found");
                    System.out.println("File not found");
                }
                String resultTransfer = readerSocket.readLine();
                if (resultTransfer.equals("Error transferring")) {
                    System.out.println(resultTransfer);
                } else if (resultTransfer.equals("Transfer correct")) {
                    System.out.println(resultTransfer);
                } else {
                    System.out.println("Error");
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
