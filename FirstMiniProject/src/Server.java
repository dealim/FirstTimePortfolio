import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import database.Insert;
import serverGui.SeverGui;


//database��! CreateTable ���� �������ּ���
//�� ���� Server ����
//�� ���� Client ����

//���� CreateTable ������ �ȵɰ��!
//SQLite3 Referenced Libraies �ٽ� �������ּ���

public class Server {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		new SeverGui();
		
		try {
			serverSocket = new ServerSocket(8888);
		} catch(IOException e) {
			e.printStackTrace();
		}


		while(true) {
			try {
				System.out.println("���ϻ����Ϸ�");
				Socket socket = serverSocket.accept();
				OutputStream out = socket.getOutputStream();

				// ������ �Է½�Ʈ���� ��´�.
				InputStream in = socket.getInputStream();
				DataInputStream dis = new DataInputStream(in);

				String client_msg = dis.readUTF();
				System.out.println(client_msg);

				String[] res = client_msg.split(" ");

				new Insert(res[0], res[1]);
				// �������� ���� ���� �����͸� ����Ѵ�.
				System.out.println("Ŭ���̾�Ʈ�κ��� ���� �޽��� :"+res[0]+" "+res[1]);      
				System.out.println("������ �����մϴ�.");

				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} // while
	} // main
}

