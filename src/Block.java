import org.lwjgl.opengl.GL11;

/**
 * represents and renders one textured 3D rectangular prism to the screen
 * @author Joe Frangoudes
 *
 */
public class Block {
	
	private float xPos;
	private float yPos;
	private float zPos;
	private float width;
	private float height;
	private float depth;
	private float dimension;
	
	/**
	 * used to create a rectangular prism of any size
	 * @param xPos
	 * @param yPos
	 * @param zPos
	 * @param width
	 * @param height
	 * @param depth
	 */
	public Block(float xPos, float yPos, float zPos, float width, float height, float depth){
		this.xPos = xPos;
		this.yPos = yPos;
		this.zPos = zPos;
		this.width = width;
		this.height = height;
		this.depth = depth;
	}
	
	/**
	 * Used for creating a perfect cube of any size
	 * where the width height and depth are the same
	 * @param xPos
	 * @param yPos
	 * @param zPos
	 * @param dimension
	 */
	public Block(float xPos, float yPos, float zPos, float dimension){
		this.xPos = xPos;
		this.yPos = yPos;
		this.zPos = zPos;
		this.dimension = dimension;
		this.width = dimension;
		this.height = dimension;
		this.depth = dimension;
	}
	
	/**
	 * renders the object with the values that it has
	 */
	public void renderObject(){
		ImageBank.getBoxTex().bind();
		GL11.glBegin(GL11.GL_QUADS);
		//front face
		GL11.glNormal3f(0.0f, 0.0f, 1.0f);
		GL11.glTexCoord2f(0.0f, 0.0f);
		GL11.glVertex3f(xPos, yPos, zPos);
		GL11.glTexCoord2f(1.0f,0.0f);
		GL11.glVertex3f(xPos+width, yPos, zPos);
		GL11.glTexCoord2f(1.0f, 1.0f);
		GL11.glVertex3f(xPos+width, yPos+height, zPos);
		GL11.glTexCoord2f(0.0f, 1.0f);
		GL11.glVertex3f(xPos, yPos+height, zPos);
		
		//back face
		GL11.glNormal3f(0.0f, 0.0f, -1.0f);
		GL11.glTexCoord2f(0.0f, 0.0f);
		GL11.glVertex3f(xPos, yPos, zPos-depth);
		GL11.glTexCoord2f(1.0f,0.0f);
		GL11.glVertex3f(xPos+width, yPos, zPos-depth);
		GL11.glTexCoord2f(1.0f, 1.0f);
		GL11.glVertex3f(xPos+width, yPos+height, zPos-depth);
		GL11.glTexCoord2f(0.0f, 1.0f);
		GL11.glVertex3f(xPos, yPos+height, zPos-depth);
		
		//right face
		GL11.glNormal3f(1.0f, 0.0f, 0.0f);
		GL11.glTexCoord2f(0.0f, 0.0f);
		GL11.glVertex3f(xPos+width, yPos, zPos);
		GL11.glTexCoord2f(1.0f,0.0f);
		GL11.glVertex3f(xPos+width, yPos, zPos-depth);
		GL11.glTexCoord2f(1.0f, 1.0f);
		GL11.glVertex3f(xPos+width, yPos+height, zPos-depth);
		GL11.glTexCoord2f(0.0f, 1.0f);
		GL11.glVertex3f(xPos+width, yPos+height, zPos);
		
		//left face
		GL11.glNormal3f(-1.0f, 0.0f, 0.0f);
		GL11.glTexCoord2f(0.0f, 0.0f);
		GL11.glVertex3f(xPos, yPos, zPos);
		GL11.glTexCoord2f(1.0f,0.0f);
		GL11.glVertex3f(xPos, yPos, zPos-depth);
		GL11.glTexCoord2f(1.0f, 1.0f);
		GL11.glVertex3f(xPos, yPos+height, zPos-depth);
		GL11.glTexCoord2f(0.0f, 1.0f);
		GL11.glVertex3f(xPos, yPos+height, zPos);
		
		//top face
		GL11.glNormal3f(0.0f, 1.0f, 0.0f);
		GL11.glTexCoord2f(0.0f, 0.0f);
		GL11.glVertex3f(xPos, yPos+height, zPos);
		GL11.glTexCoord2f(1.0f,0.0f);
		GL11.glVertex3f(xPos, yPos+height, zPos-depth);
		GL11.glTexCoord2f(1.0f, 1.0f);
		GL11.glVertex3f(xPos+width, yPos+height, zPos-depth);
		GL11.glTexCoord2f(0.0f, 1.0f);
		GL11.glVertex3f(xPos+width, yPos+height, zPos);
		
		//bottom face
		GL11.glNormal3f(0.0f, -1.0f, 0.0f);
		GL11.glTexCoord2f(0.0f, 0.0f);
		GL11.glVertex3f(xPos, yPos, zPos);
		GL11.glTexCoord2f(1.0f,0.0f);
		GL11.glVertex3f(xPos, yPos, zPos-depth);
		GL11.glTexCoord2f(1.0f, 1.0f);
		GL11.glVertex3f(xPos+width, yPos, zPos-depth);
		GL11.glTexCoord2f(0.0f, 1.0f);
		GL11.glVertex3f(xPos+width, yPos, zPos);
		GL11.glEnd();
	}

	/**
	 * @return the xPos
	 */
	public float getxPos() {
		return xPos;
	}

	/**
	 * @param xPos the xPos to set
	 */
	public void setxPos(float xPos) {
		this.xPos = xPos;
	}

	/**
	 * @return the yPos
	 */
	public float getyPos() {
		return yPos;
	}

	/**
	 * @param yPos the yPos to set
	 */
	public void setyPos(float yPos) {
		this.yPos = yPos;
	}
	
	/**
	 * @return the zPos
	 */
	public float getzPos() {
		return zPos;
	}

	/**
	 * @param zPos the zPos to set
	 */
	public void setzPos(float zPos) {
		this.zPos = zPos;
	}

	/**
	 * @return the width
	 */
	public float getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(float width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public float getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(float height) {
		this.height = height;
	}

	/**
	 * @return the depth
	 */
	public float getDepth() {
		return depth;
	}

	/**
	 * @param depth the depth to set
	 */
	public void setDepth(float depth) {
		this.depth = depth;
	}

	/**
	 * @return the dimension
	 */
	public float getDimention() {
		return dimension;
	}

	/**
	 * @param dimention the dimension to set
	 */
	public void setDimention(float dimention) {
		this.dimension = dimention;
	}
}
