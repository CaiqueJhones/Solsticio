/*
 * Key.java
 *
 * Copyright 2016 Caique Jhones
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.solsticio.core.input;

/**
 * Representa as teclas básicas do teclado.
 * 
 * @author Caique Jhones
 * @version 1
 * @since 0.1.0
 */
public enum Key {
	
	NUM0(0x30),
	NUM1(0x31),
	NUM2(0x32),
	NUM3(0x33),
	NUM4(0x34),
	NUM5(0x35),
	NUM6(0x36),
	NUM7(0x37),
	NUM8(0x38),
	NUM9(0x39),
	
	NUMPAD0(0x60),
	NUMPAD1(0x61),
	NUMPAD2(0x62),
	NUMPAD3(0x63),
	NUMPAD4(0x64),
	NUMPAD5(0x65),
	NUMPAD6(0x66),
	NUMPAD7(0x67),
	NUMPAD8(0x68),
	NUMPAD9(0x69),
	MULTIPLY(0x6A),
	ADD(0x6B),
	
	A(0x41),
	B(0x42),
	C(0x43),
	D(0x44),
	E(0x45),
	F(0x46),
	G(0x47),
	H(0x48),
	I(0x49),
	J(0x4A),
	K(0x4B),
	L(0x4C),
	M(0x4D),
	N(0x4E),
	O(0x4F),
	P(0x50),
	Q(0x51),
	R(0x52),
	S(0x53),
	T(0x54),
	U(0x55),
	V(0x56),
	W(0x57),
	X(0x58),
	Y(0x59),
	Z(0x5A),
	
	ENTER     ('\n'),
	BACK_SPACE('\b'),
	TAB       ('\t'),
	CANCEL    (0x03),
	CLEAR     (0x0C),
	SHIFT     (0x10),
	CONTROL   (0x11),
	ALT       (0x12),
	PAUSE     (0x13),
	CAPS_LOCK (0x14),
	ESCAPE    (0x1B),
	SPACE     (0x20),
	PAGE_UP   (0x21),
	PAGE_DOWN (0x22),
	END       (0x23),
	HOME      (0x24),
	
	LEFT(0x25),
	UP(0x26),
	RIGHT(0x27),
	DOWN(0x28),
	
	/** ","*/
	COMMA(0x2C),
	/** "-"*/
	MINUS(0x2D),
	/** "."*/
	PERIOD(0x2E),
	/** "/"*/
	SLASH(0x2F),
	/** ";"*/
	SEMICOLON(0x3B),
	/** "="*/
	EQUALS(0x3D),
	/** "["*/
	OPEN_BRACKET(0x5B),
	/** "\"*/
	BACK_SLASH(0x5C),
	/** "]"*/
	CLOSE_BRACKET(0x5D),
	/** Numpad Separator key */
	SEPARATOR(0x6C),
	
	SUBTRACT(0x6D),
	DECIMAL(0x6E),
	DIVIDE(0x6F),
	DELETE(0x7F),
	NUM_LOCK(0x90),
	SCROLL_LOCK(0x91),
	
	F1(0x70),
	F2(0x71),
	F3(0x72),
	F4(0x73),
	F5(0x74),
	F6(0x75),
	F7(0x76),
	F8(0x77),
	F9(0x78),
	F10(0x79),
	F11(0x7A),
	F12(0x7B),
	
	PRINTSCREEN(0x9A),
	INSERT(0x9B),
	HELP(0x9C),
	META(0x9D),
	BACK_QUOTE(0xC0),
	QUOTE(0xDE),
	
	KP_LEFT(0xE0),
	KP_UP(0xE1),
	KP_RIGHT(0xE2),
	KP_DOWN(0xE3),
	
	AMPERSAND(0x96),
	ASTERISK(0x97),                             
	QUOTEDBL(0x98),                            
	LESS(0x99),                              
	GREATER(0xA0),                             
	BRACELEFT(0xA1),                             
	BRACERIGHT(0xA2),
	
	/** "@"*/
	AT(0x200),
	/** ":"*/
	COLON(0x201),
	/** "^" */
	CIRCUMFLEX(0x202),
	/** "$"*/
	DOLLAR(0x203),
	EURO_SIGN(0x204),
	/** "!"*/
	EXCLAMATION(0x206),
	/** "("*/
	LEFT_PARENTHESIS(0x207),
	/** "#"*/
	NUMBER_SIGN(0x208),
	/** "+"*/
	PLUS(0x209),
	/** ")"*/
	RIGHT_PARENTHESIS(0x20A),
	/** "_"*/
	UNDERSCORE(0x20B),
	WINDOWS(0x20C),
	
	/**
     * Events which do not map to a
     * valid Unicode character use this for the keyChar value.
     */
    CHAR_UNDEFINED(0xFFFF);
	
	private int code;

	private Key(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static Key valueOf(int keyCode) {
		Key[] values = values();
		for (Key key : values) {
			if (key.code == keyCode)
				return key;
		}
		return Key.CHAR_UNDEFINED;
	}
}
