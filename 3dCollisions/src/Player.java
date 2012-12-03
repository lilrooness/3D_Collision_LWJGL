
public class Player {
	
	private boolean left, right, forward, back;
	private float xPos, yPos, zPos, lx, lz, vel, turnSpeed, currentRadAngle, boundingSphereRad;
	
	public Player(float xPos, float yPos, float zPos, float lx, float lz){
		this.xPos = xPos;
		this.yPos = yPos;
		this.zPos = zPos;
		this.lx = lx;
		this.lz = lz;
		
		left = false;
		right = false;
		forward = false;
		back = false;
		
		vel = 0.01f;
		turnSpeed = 0.001f;
		currentRadAngle = 0.0f;
		boundingSphereRad = 3f;
	}

	public void update(){
		if(left || right){
			if(right){
				currentRadAngle+= turnSpeed;
			}
			if(left){
				currentRadAngle-= turnSpeed;
			}
			lx = -(float)Math.sin(currentRadAngle);
			lz = (float)Math.cos(currentRadAngle);
		}
		if(forward){
			xPos = xPos+lx*vel;
			zPos = zPos+lz*vel;
		}
		if(back){
			xPos = xPos-lx*vel;
			zPos = zPos-lz*vel;
		}
		
		float magnitude = (float) Math.sqrt(lx*lx + lz*lz);
		lz = lz / magnitude;
		lx = lx / magnitude;
	}

	/**
	 * @return the left
	 */
	public boolean isLeft() {
		return left;
	}

	/**
	 * @param left the left to set
	 */
	public void setLeft(boolean left) {
		this.left = left;
	}

	/**
	 * @return the right
	 */
	public boolean isRight() {
		return right;
	}

	/**
	 * @param right the right to set
	 */
	public void setRight(boolean right) {
		this.right = right;
	}

	/**
	 * @return the forward
	 */
	public boolean isForward() {
		return forward;
	}

	/**
	 * @param forward the forward to set
	 */
	public void setForward(boolean forward) {
		this.forward = forward;
	}

	/**
	 * @return the back
	 */
	public boolean isBack() {
		return back;
	}

	/**
	 * @param back the back to set
	 */
	public void setBack(boolean back) {
		this.back = back;
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
	 * @return the lx
	 */
	public float getLx() {
		return lx;
	}

	/**
	 * @param lx the lx to set
	 */
	public void setLx(float lx) {
		this.lx = lx;
	}

	/**
	 * @return the lz
	 */
	public float getLz() {
		return lz;
	}

	/**
	 * @param lz the lz to set
	 */
	public void setLz(float lz) {
		this.lz = lz;
	}

	/**
	 * @return the vel
	 */
	public float getVel() {
		return vel;
	}

	/**
	 * @param vel the vel to set
	 */
	public void setVel(float vel) {
		this.vel = vel;
	}

	/**
	 * @return the turnSpeed
	 */
	public float getTurnSpeed() {
		return turnSpeed;
	}

	/**
	 * @param turnSpeed the turnSpeed to set
	 */
	public void setTurnSpeed(float turnSpeed) {
		this.turnSpeed = turnSpeed;
	}

	/**
	 * @return the currentRadAngle
	 */
	public float getCurrentRadAngle() {
		return currentRadAngle;
	}

	/**
	 * @param currentRadAngle the currentRadAngle to set
	 */
	public void setCurrentRadAngle(float currentRadAngle) {
		this.currentRadAngle = currentRadAngle;
	}

	/**
	 * @return the boundingSphereRad
	 */
	public float getBoundingSphereRad() {
		return boundingSphereRad;
	}

	/**
	 * @param boundingSphereRad the boundingSphereRad to set
	 */
	public void setBoundingSphereRad(float boundingSphereRad) {
		this.boundingSphereRad = boundingSphereRad;
	}
	
}
