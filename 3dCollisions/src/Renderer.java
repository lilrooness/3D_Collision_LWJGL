import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.*;
import org.lwjgl.util.glu.*;
import static org.lwjgl.opengl.GL11.*;

public class Renderer {
	
	public static void main(String[] args) {
		
		try {
			Display.setDisplayMode(new DisplayMode(600, 400));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		Player p1 = new Player(0, 0, -10, 0, 0);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthFunc(GL11.GL_LEQUAL);
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		GLU.gluPerspective(45, 600/400, 1, 100);
		glMatrixMode(GL_MODELVIEW);
		GLU.gluLookAt(p1.getxPos(), p1.getyPos(), p1.getzPos(), 
				  p1.getLx(), 0, p1.getLz(), 
				  0, 1, 0);
		glClearColor(0.0f, 0.0f, 0.0f, 1);
		glColor3f(1, 1, 1);
		ImageBank.texInit();
		
		Wall w = new Wall(0f, -4f, 0f, 10f, 10f);
		
		while(!Display.isCloseRequested()){
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			glLoadIdentity();
			GLU.gluLookAt(p1.getxPos(), p1.getyPos(), p1.getzPos(), 
					  p1.getxPos()+p1.getLx(), p1.getyPos(), p1.getzPos()+p1.getLz(),
					  0, 1, 0);
			w.render();
			
			//get user input
			if(Keyboard.isKeyDown(Keyboard.KEY_W)){
				p1.setForward(true);
			}else{
				p1.setForward(false);
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_S)){
				p1.setBack(true);
			}else{
				p1.setBack(false);
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_A)){
				p1.setLeft(true);
			}else{
				p1.setLeft(false);
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_D)){
				p1.setRight(true);
			}else{
				p1.setRight(false);
			}
			
			if(w.isCollision(p1)){
				glColor3f(1, 0, 0);
				System.out.println("collision !");
			}else{
				glColor3f(1, 1, 1);
			}
			
			p1.update();
			Display.update();
		}	
		Display.destroy();
	}
}
