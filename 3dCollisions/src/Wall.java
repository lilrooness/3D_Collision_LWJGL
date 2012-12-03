import static org.lwjgl.opengl.GL11.*;

public class Wall {
	private float xPos, yPos, zPos, xPos1, yPos1, zPos1, xPos2, yPos2, zPos2, xPos3, yPos3, zPos3;
	private float[] n;
	private float[] normalToPlayer;
	
	public Wall(float xPos, float yPos, float zPos, float xPos1, float yPos1, float zPos1, float xPos2, float yPos2, float zPos2, float xPos3, float yPos3, float zPos3){
		this.xPos = xPos;
		this.yPos = yPos;
		this.zPos = zPos;
		
		this.xPos1 = xPos1;
		this.yPos1 = yPos1;
		this.zPos1 = zPos1;
		
		this.xPos2 = xPos2;
		this.yPos2 = yPos2;
		this.zPos2 = zPos2;
		
		this.xPos3 = xPos3;
		this.yPos3 = yPos3;
		this.zPos3 = zPos3;
		n = calculateNormal();
		float nMag = euDist(n[0], n[1], n[2], 0, 0, 0);
		n[0] = n[0]/nMag;
		n[1] = n[1]/nMag;
		n[2] = n[2]/nMag;
		normalToPlayer = new float[3];
	}
	
	public void render(){
		ImageBank.getBoxTex().bind();
		glBegin(GL_QUADS);
		glVertex3f(xPos, yPos, zPos);
		glTexCoord2f(0.0f, 0.0f);
		glVertex3f(xPos3, yPos3, zPos3);
		glTexCoord2f(1.0f,0.0f);
		glVertex3f(xPos2, yPos2, zPos2);
		glTexCoord2f(1.0f, 1.0f);
		glVertex3f(xPos1, yPos1, zPos1);
		glTexCoord2f(0.0f, 1.0f);
		glEnd();
	}
	
	/**
	 * @param p
	 * @return true if the players directional vector intersects the plane
	 */
	private boolean planeCollision(Player p){
		boolean c= false;
		
		float prod = p.getLx() * n[0] + 0f * n[1] + p.getLz() * n[2];
		if(prod == 0){
			c = false;
		}else{
			c = true;
		}
		return c;
	}
	
	/**
	 * 
	 * @param p
	 * @return the point of intersection between players directional vector and the plane
	 */
	float[] getPlaneInterception(Player p){
		float[] vec = new float[3];
		vec[0] = xPos - (xPos2);
		vec[1] = yPos - (yPos2);
		vec[2] = zPos - zPos2;
		
		float ax = n[0] * vec[0];
		float ay = n[1] * vec[1];
		float az = n[2] * vec[2];
		float result = ax + ay + az;

		float s = (n[0]*p.getxPos() + n[1]*0 + n[2]*p.getzPos() - result)
				  /
				  (n[0]*p.getLx() - n[1]*0 - n[2]*p.getLz());
		
		float i[] = {p.getxPos() + p.getLx() * s, 0f, p.getzPos() + p.getLz() * s};
		//Block b = new Block(i[0], i[1], i[2], 3f, 3f, 3f);
		//b.renderObject();
		return i;
	}
	
	/**
	 * returns true if players look vector is colliding with the quad
	 * @param p
	 * @return
	 */
	public boolean isCollision(Player p){
		float i[];
		boolean collision;
		if(planeCollision(p)){
			i = getPlaneInterception(p);
			if(inTriangle(xPos, yPos, zPos, xPos2, yPos2, zPos2, xPos1, yPos1, zPos1, i[0], i[1], i[2])){
				collision = true;
			}else if(inTriangle(xPos, yPos, zPos, xPos3, yPos3, zPos3, xPos2, yPos2, zPos2, i[0], i[1], i[2])){
				collision = true;
			}else{
				collision = false;
			}
			
		}else{
			collision = false;
		}
		return collision;
	}
	
	/**
	 * @param p
	 * @return
	 */
	public boolean quadSphereCollision(Player p){
		boolean collision = false;
		float[] vec = new float[3];
		vec[0] = xPos - (xPos2);
		vec[1] = yPos - (yPos2);
		vec[2] = zPos - zPos2;
		
		float ax = n[0] * vec[0];
		float ay = n[1] * vec[1];
		float az = n[2] * vec[2];
		float result = ax + ay + az;
		
		float s = (n[0]*p.getxPos() + n[1]*-n[1] + n[2]*p.getzPos() - result)
				  /
				  (n[0]*(-1*n[0]) - n[1]*(-1*n[1]) - n[2]*(-1*n[2]));
		
		
		float i[] = {p.getxPos() + (-n[0]) * s, (-n[1]), p.getzPos() + (-n[2]) * s};
		normalToPlayer[0] = i[0] - p.getxPos();
		normalToPlayer[1] = 0;
		normalToPlayer[2] = i[2] - p.getzPos();
		
		if(inTriangle(xPos, yPos, zPos, xPos2, yPos2, zPos2, xPos1, yPos1, zPos1, i[0], i[1], i[2])){
			if(euDist(i[0], i[1], i[2], p.getxPos(), p.getyPos(), p.getzPos()) < p.getBoundingSphereRad()){
				collision = true;
			}
		}else if(inTriangle(xPos, yPos, zPos, xPos3, yPos3, zPos3, xPos2, yPos2, zPos2, i[0], i[1], i[2])){
			if(euDist(i[0], i[1], i[2], p.getxPos(), p.getyPos(), p.getzPos()) < p.getBoundingSphereRad()){
				collision = true;
			}
		}else{
			collision = false;
		}
		
		return collision;
	}
	
	/**
	 * @param x1
	 * @param y1
	 * @param z1
	 * @param x2
	 * @param y2
	 * @param z2
	 * @param x3
	 * @param y3
	 * @param z3
	 * @param x
	 * @param y
	 * @param z
	 * @return true if the point x, y, z is inside the triangle 1, 2, 3
	 */
	private boolean inTriangle(float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3, float x, float y, float z){
		boolean collision;
		float area = 0f;
		area += triangleArea(x, y, z, x1, y1, z1, x2, y2, z2);
		area += triangleArea(x, y, z, x1, y1, z1, x3, y3, z3);
		area += triangleArea(x, y, z, x2, y2, z2, x3, y3, z3);
		
		float bigArea = triangleArea(x1, y1, z1, x2, y2, z2, x3, y3, z3);
		
		if(bigArea == Math.round(area)){
			collision = true;
		}else{
			collision = false;
		}
		
		return collision;
	}
	
	/**
	 * 
	 * @param x1
	 * @param y1
	 * @param z1
	 * @param x2
	 * @param y2
	 * @param z2
	 * @param x3
	 * @param y3
	 * @param z3
	 * @return the area of the triangle 1, 2, 3
	 */
	private float triangleArea(float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3){
		float a = euDist(x1, y1, z1, x2, y2, z2);
		float b = euDist(x1, y1, z1, x3, y3, z3);
		float c = euDist(x2, y2, z2, x3, y3, z3);
		float  s = (a + b + c) / 2.0f;
		float x = s*(s - a)*(s - b)*(s - c);
		if(x < 0){
			x *= -1f;
		}
		return (float)Math.sqrt(x);
	}
	
	/**
	 * 
	 * @param x1
	 * @param y1
	 * @param z1
	 * @param x2
	 * @param y2
	 * @param z2
	 * @return the Euclidean distance between points 1 and 2 (magnitude of the vector between 1 and 2)
	 */
	private float euDist(float x1, float y1, float z1, float x2, float y2, float z2){
		float x = ((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2)) + ((z1 - z2) * (z1 - z2));
		if(x < 0){
			x *= -1f;
		}
		return (float)Math.sqrt(x);
	}
	
	/**
	 * @return the normal vector for the plane
	 */
	private float[] calculateNormal(){
		float[] n = new float[3];
		
		//3 points on the whole plane
		float a[] = {xPos, yPos, zPos};
		float b[] = {xPos2, yPos2, zPos2};
		float c[] = {xPos1, yPos1, zPos1};
		
		//construct two vectors from points
		float vec1[] = {a[0] - b[0],  a[1] - b[1], a[2] - b[2]};
		float vec2[] = {a[0] - c[0], a[1] - c[1], a[2] - c[2]};
		
		//calculate cross product (normal)
		n[0] = vec1[1] * vec2[2] - vec1[2] * vec2[1];
		n[1] = vec1[2] * vec2[0] - vec1[0] * vec2[2];
		n[2] = vec1[0] * vec2[1] - vec1[1] * vec2[0];
		
		return n;
	}
	
	public void collision_response(Player p){
		if(isCollision(p)){
			if(quadSphereCollision(p)){
				p.setzPos(p.getzPos()-normalToPlayer[2]*p.getVel());
				p.setxPos(p.getxPos()-normalToPlayer[0]*p.getVel());
			}
		}
	}
}