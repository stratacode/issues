package sc.opengl;

import java.util.List;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GL2ES1;
import javax.media.opengl.GL2GL3;
import javax.media.opengl.glu.GLU;

import javax.media.opengl.fixedfunc.GLLightingFunc;
import javax.media.opengl.fixedfunc.GLMatrixFunc;

import sc.opengl.CCanvas;
import sc.opengl.CRotate;
import sc.opengl.CTranslate;
import sc.opengl.CShape;
import sc.opengl.CColor;
import sc.opengl.CText;
import sc.opengl.CGroup;
import sc.opengl.CLabel;
import sc.opengl.CTree;
import sc.opengl.CTextView;
import sc.opengl.CAxis;
import sc.opengl.CRect;
import sc.opengl.CPolygonStipple;
import sc.opengl.CLineStipple;

import sc.opengl.IRenderNode;
import sc.opengl.IRenderParent;

opengl.main extends lib, swing.core, util {
   codeType = sc.layer.CodeType.Framework;
   codeFunction = sc.layer.CodeFunction.UI;

   compiledOnly = true;
}
