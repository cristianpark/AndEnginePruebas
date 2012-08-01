package cursoAndroid.cursoandroid;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.Entity;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.graphics.Point;
import android.util.Log;

public class GameScene extends Scene implements IOnSceneTouchListener {

	private static final int LAYER_BACKGROUND = 0;
	private static final int LAYER_BARCOS = 1;
	private static final int LAYER_LINEAS = 2;
	private Point PosIni;

	public Tablero tablero;
	Camera mCamera;
	public static int AnchoCuadro = MainActivity.CAMERA_HEIGHT / 10;
	public static int AltoCuadro = MainActivity.CAMERA_HEIGHT / 10;
	public int indPaleta;

	public GameScene() {

		mCamera = MainActivity.getSharedInstance().mCamera;

		this.setBackgroundEnabled(false);
		for (int i = 0; i < 4; i++) {
			this.attachChild(new Entity());
		}
		indPaleta = 0;
		setOnSceneTouchListener(this);

	}

	public void Render1() {
		// TiledTextureRegion bar = MainActivity.getSharedInstance().mBarco;
		VertexBufferObjectManager vert = MainActivity.getSharedInstance()
				.getVertexBufferObjectManager();

		ITextureRegion mBackgroundTextureRegion = MainActivity
				.getSharedInstance().mBackgroundTextureRegion;

		this.getChildByIndex(LAYER_BACKGROUND).attachChild(
				new Sprite(0, 0, mBackgroundTextureRegion, vert));

		int inc = MainActivity.CAMERA_HEIGHT / 10;

		int xIni = (MainActivity.CAMERA_WIDTH - MainActivity.CAMERA_HEIGHT) / 2;

		int yIni = 0;
		for (int i = 0; i < 11; i++) {
			final Rectangle cuadroh = new Rectangle(xIni, yIni + (i * inc),
					MainActivity.CAMERA_HEIGHT, 2, vert);
			final Rectangle cuadrov = new Rectangle(xIni + (i * inc), yIni, 2,
					MainActivity.CAMERA_HEIGHT, vert);
			cuadroh.setColor(1, 1, 1);
			cuadrov.setColor(1, 1, 1);
			this.getChildByIndex(LAYER_LINEAS).attachChild(cuadroh);
			this.getChildByIndex(LAYER_LINEAS).attachChild(cuadrov);
		}
	}

	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {

		float x = pSceneTouchEvent.getX();
		float y = pSceneTouchEvent.getY();

		int inc = MainActivity.CAMERA_HEIGHT / 10;
		int xIni = (MainActivity.CAMERA_WIDTH - MainActivity.CAMERA_HEIGHT) / 2;
		int posx = (int) ((x - xIni) / inc);
		int posy = (int) (y / inc);

		if (PosIni == null) {
			PosIni = new Point(posx, posy);
			Log.i("ini", "posicion " + posx + " " + posy);
		} else {
			Point PosFin = new Point(posx, posy);
			Log.i("fin", "posicion " + posx + " " + posy);
			if (MainActivity.mTablero.CrearBarco(PosIni, PosFin)) {
				TiledTextureRegion barc = MainActivity.getSharedInstance().mBarco;
				VertexBufferObjectManager vert = MainActivity
						.getSharedInstance().getVertexBufferObjectManager();
				final Sprite barco1 = new Sprite((PosIni.x * inc) + xIni,
						PosIni.y * inc, AnchoCuadro, AltoCuadro,
						barc.getTextureRegion(1), vert);
				final Sprite barco2 = new Sprite((PosFin.x * inc) + xIni,
						PosFin.y * inc, AnchoCuadro, AltoCuadro,
						barc.getTextureRegion(1), vert);
				pScene.getChildByIndex(LAYER_BARCOS).attachChild(barco1);
				pScene.getChildByIndex(LAYER_BARCOS).attachChild(barco2);
				PosIni = null;
				Log.i("fin", "dibujado ");
			}

		}
		return true;
	}

}
