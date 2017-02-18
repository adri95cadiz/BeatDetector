/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bpmreader;

import java.util.ArrayList;

public class KeyFinder {
	
	static int NumberOfElements;
	static int Index;
	static int CurrentIndex;
	private static String KEYval;
	private static String AccidentalVal;
	
	final int TotalKeyNumberID = 15;

	final int[] KeyNumberID = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
	
	final String[] MajorKeyID = { "C", "G", "D", "A", "E", "B", "F#", "C#", "F",
			"Bb", "Eb", "Ab","Db", "Gb", "Cb"};

	public KeyFinder(String KEYval, String AccidentalVal) {
        KeyFinder.setKEYval(KEYval);
        KeyFinder.setAccidentalVal(AccidentalVal);
    }
	
	public String getKEYval() {
		return KEYval;
	}

	public static void setKEYval(String kEYval) {
		KEYval = kEYval;
	}

	public static String getAccidentalVal() {
		return AccidentalVal;
	}

	public static void setAccidentalVal(String accidentalVal) {
		AccidentalVal = accidentalVal;
	}
	
	/********************************************************************/
	/*          GetKeyIntegerID method                                  */
	/********************************************************************/
	public int GetKeyIntegerID(String KeyVal) {
		int retKeyIntegerVal = 0;
		
		for(int i = 0; i < TotalKeyNumberID - 1; i++) {
			if (KeyVal == MajorKeyID[i]) {
				retKeyIntegerVal = KeyNumberID[i];
			}
		}
		return retKeyIntegerVal;
	}
	
	/********************************************************************/
	/*          GetKeyStringID method                                   */
	/********************************************************************/
	public String GetKeyStringID(int KeyVal) {
		String retKeyStringVal = "Unknown";
		
		for(int i = 0; i < TotalKeyNumberID - 1; i++) {
			if (KeyVal == KeyNumberID[i]) {
				retKeyStringVal = MajorKeyID[i];
			}
		}
		return retKeyStringVal;
	}
	
	/********************************************************************/
	/*          DetermineKey method - key detection algorithm           */
	/********************************************************************/
	public void DetermineKey(ArrayList<String> NoteArray) {
		
		/* The two most famous key detection algorithms are the         */
		/* Krumhansl-Schmuckler and the Temperley algorithms            */
		/* This method is a simplified version for detecting the key    */
		/* assuming single key music input, it detects the music key    */
		/* and accidentals based on:                                    */
		/* - Accidental deduction based on other notes in proximity     */
		/* - Note combinations and key profiling                        */
		/* - Circle of fifths                                           */
		
		/* Natural notes A, B, C, D, E, F, G counters */
		int Total_A = 0;
		int Total_B = 0;
		int Total_C = 0;
		int Total_D = 0;
		int Total_E = 0;
		int Total_F = 0;
		int Total_G = 0;
		
		/* Blacknotes with accidentals #/b counters */
		int Total_Fsharp_or_Gb = 0;
		int Total_Asharp_or_Bb = 0;
		int Total_Gsharp_or_Ab = 0;
		int Total_Dsharp_or_Eb = 0;
		int Total_Csharp_or_Db = 0;
		
		/* KEY Weight values */
		int CKeyWeight = 0;
		int GKeyWeight = 0;
		int DKeyWeight = 0;
		int AKeyWeight = 0;
		int EKeyWeight = 0;
		int BKeyWeight = 0;
		int FsharpKeyWeight = 0;
		int CsharpKeyWeight = 0;
		int FKeyWeight = 0;
		int BbKeyWeight = 0;
		int EbKeyWeight = 0;
		int AbKeyWeight = 0;
		int DbKeyWeight = 0;
		int GbKeyWeight = 0;
		int CbKeyWeight = 0;
		
		/* Variable Initialisation */
		NumberOfElements = NoteArray.size();

		/* Getting parameters needed for KEY profile and Accidental value */
		for (Index=0; Index < NoteArray.size(); Index++) {
			
			if (("A7".equals(NoteArray.get(Index))) ||
					("A6".equals(NoteArray.get(Index))) ||
					("A5".equals(NoteArray.get(Index))) ||
					("A4".equals(NoteArray.get(Index))) ||
					("A3".equals(NoteArray.get(Index))) ||
					("A2".equals(NoteArray.get(Index))) ||
					("A1".equals(NoteArray.get(Index))) ||
					("A0".equals(NoteArray.get(Index)))) {
				Total_A++;
			}
			
			if (("B7".equals(NoteArray.get(Index))) ||
					("B6".equals(NoteArray.get(Index))) ||
					("B5".equals(NoteArray.get(Index))) ||
					("B4".equals(NoteArray.get(Index))) ||
					("B3".equals(NoteArray.get(Index))) ||
					("B2".equals(NoteArray.get(Index))) ||
					("B1".equals(NoteArray.get(Index))) ||
					("B0".equals(NoteArray.get(Index)))) {
				Total_B++;
			}
			
			if (("C8".equals(NoteArray.get(Index))) ||
					("C7".equals(NoteArray.get(Index))) ||
					("C6".equals(NoteArray.get(Index))) ||
					("C5".equals(NoteArray.get(Index))) ||
					("C4".equals(NoteArray.get(Index))) ||
					("C3".equals(NoteArray.get(Index))) ||
					("C2".equals(NoteArray.get(Index))) ||
					("C1".equals(NoteArray.get(Index)))) {
				Total_C++;
			}
			
			if (("D7".equals(NoteArray.get(Index))) ||
					("D6".equals(NoteArray.get(Index))) ||
					("D5".equals(NoteArray.get(Index))) ||
					("D4".equals(NoteArray.get(Index))) ||
					("D3".equals(NoteArray.get(Index))) ||
					("D2".equals(NoteArray.get(Index))) ||
					("D1".equals(NoteArray.get(Index)))) {
				Total_D++;
			}
			
			if (("E7".equals(NoteArray.get(Index))) ||
					("E6".equals(NoteArray.get(Index))) ||
					("E5".equals(NoteArray.get(Index))) ||
					("E4".equals(NoteArray.get(Index))) ||
					("E3".equals(NoteArray.get(Index))) ||
					("E2".equals(NoteArray.get(Index))) ||
					("E1".equals(NoteArray.get(Index)))) {
				Total_E++;
			}
			
			if (("F7".equals(NoteArray.get(Index))) ||
					("F6".equals(NoteArray.get(Index))) ||
					("F5".equals(NoteArray.get(Index))) ||
					("F4".equals(NoteArray.get(Index))) ||
					("F3".equals(NoteArray.get(Index))) ||
					("F2".equals(NoteArray.get(Index))) ||
					("F1".equals(NoteArray.get(Index)))) {
				Total_F++;
			}
			
			if (("F#7_or_Gb7".equals(NoteArray.get(Index))) ||
					("F#6_or_Gb6".equals(NoteArray.get(Index))) ||
					("F#5_or_Gb5".equals(NoteArray.get(Index))) ||
					("F#4_or_Gb4".equals(NoteArray.get(Index))) ||
					("F#3_or_Gb3".equals(NoteArray.get(Index))) ||
					("F#2_or_Gb2".equals(NoteArray.get(Index))) ||
					("F#1_or_Gb1".equals(NoteArray.get(Index)))) {
				Total_Fsharp_or_Gb++;
			}
			
			if (("A#7_or_Bb7".equals(NoteArray.get(Index))) ||
					("A#6_or_Bb6".equals(NoteArray.get(Index))) ||
					("A#5_or_Bb5".equals(NoteArray.get(Index))) ||
					("A#4_or_Bb4".equals(NoteArray.get(Index))) ||
					("A#3_or_Bb3".equals(NoteArray.get(Index))) ||
					("A#2_or_Bb2".equals(NoteArray.get(Index))) ||
					("A#1_or_Bb1".equals(NoteArray.get(Index)))) {
				Total_Asharp_or_Bb++;
			}
			
			if (("G#7_or_Ab7".equals(NoteArray.get(Index))) ||
					("G#6_or_Ab6".equals(NoteArray.get(Index))) ||
					("G#5_or_Ab5".equals(NoteArray.get(Index))) ||
					("G#4_or_Ab4".equals(NoteArray.get(Index))) ||
					("G#3_or_Ab3".equals(NoteArray.get(Index))) ||
					("G#2_or_Ab2".equals(NoteArray.get(Index))) ||
					("G#1_or_Ab1".equals(NoteArray.get(Index)))) {
				Total_Gsharp_or_Ab++;
			}
			
			if (("D#7_or_Eb7".equals(NoteArray.get(Index))) ||
					("D#6_or_Eb6".equals(NoteArray.get(Index))) ||
					("D#5_or_Eb5".equals(NoteArray.get(Index))) ||
					("D#4_or_Eb4".equals(NoteArray.get(Index))) ||
					("D#3_or_Eb3".equals(NoteArray.get(Index))) ||
					("D#2_or_Eb2".equals(NoteArray.get(Index))) ||
					("D#1_or_Eb1".equals(NoteArray.get(Index)))) {
				Total_Dsharp_or_Eb++;
			}
			
			if (("C#7_or_Db7".equals(NoteArray.get(Index))) ||
					("C#6_or_Db6".equals(NoteArray.get(Index))) ||
					("C#5_or_Db5".equals(NoteArray.get(Index))) ||
					("C#4_or_Db4".equals(NoteArray.get(Index))) ||
					("C#3_or_Db3".equals(NoteArray.get(Index))) ||
					("C#2_or_Db2".equals(NoteArray.get(Index))) ||
					("C#1_or_Db1".equals(NoteArray.get(Index)))) {
				Total_Csharp_or_Db++;
			}
		}
		
		/* Calculating KEY profile weights                                    */
		/* Note from Luis:                                                    */
		/* Temperley suggested a combination of the Krumhansl-Schmuckler and  */
		/* Longuet-Higgins/Steedman approaches for key detection, by using    */
		/* note detection as 1 (present) and (0) not present. This works OK   */
		/* for short music passages.                                          */
		CKeyWeight = Total_A + Total_B + Total_C + Total_D + Total_E +
				Total_F + Total_G;
		GKeyWeight = Total_A + Total_B + Total_C + Total_D + Total_E +
				Total_Fsharp_or_Gb + Total_G;
		DKeyWeight = Total_A + Total_B + Total_Csharp_or_Db + Total_D +
				Total_E + Total_Fsharp_or_Gb + Total_G;
		AKeyWeight = Total_A + Total_B + Total_Csharp_or_Db + Total_D +
				Total_E + Total_Fsharp_or_Gb + Total_Gsharp_or_Ab;
		EKeyWeight = Total_A + Total_B + Total_Csharp_or_Db + Total_Dsharp_or_Eb +
				Total_E + Total_Fsharp_or_Gb + Total_Gsharp_or_Ab;
		BKeyWeight = Total_Asharp_or_Bb + Total_B + Total_Csharp_or_Db +
				Total_Dsharp_or_Eb + Total_E + Total_Fsharp_or_Gb + Total_Gsharp_or_Ab;
		FsharpKeyWeight = Total_Asharp_or_Bb + Total_B + Total_Csharp_or_Db +
				Total_Dsharp_or_Eb + Total_Fsharp_or_Gb + Total_Gsharp_or_Ab;
		CsharpKeyWeight = Total_Asharp_or_Bb + Total_Csharp_or_Db + Total_Dsharp_or_Eb +
				Total_E + Total_Fsharp_or_Gb + Total_Gsharp_or_Ab;
		FKeyWeight = Total_A + Total_Asharp_or_Bb + Total_C + Total_D +
				Total_E + Total_F + Total_G;
		BbKeyWeight = Total_A + Total_Asharp_or_Bb + Total_C + Total_D +
				Total_Dsharp_or_Eb + Total_F + Total_G;
		EbKeyWeight = Total_Gsharp_or_Ab + Total_Asharp_or_Bb + Total_C +
				Total_D + Total_Dsharp_or_Eb + Total_F + Total_G;
		AbKeyWeight = Total_Gsharp_or_Ab + Total_Asharp_or_Bb + Total_C +
				Total_Csharp_or_Db + Total_Dsharp_or_Eb + Total_F + Total_G;
		DbKeyWeight = Total_Gsharp_or_Ab + Total_Asharp_or_Bb + Total_C +
				Total_Csharp_or_Db + Total_Dsharp_or_Eb + Total_F + Total_Fsharp_or_Gb;
		GbKeyWeight = Total_Gsharp_or_Ab + Total_Asharp_or_Bb + Total_Csharp_or_Db +
				Total_Dsharp_or_Eb + Total_F + Total_Fsharp_or_Gb;
		CbKeyWeight = Total_Gsharp_or_Ab + Total_Asharp_or_Bb + Total_Csharp_or_Db +
				Total_Dsharp_or_Eb + Total_Fsharp_or_Gb;
		
		/* Criteria 1 - Highest correlation of possible notes in a key yields the */
		/*              estimated key.                                            */
		if ((Total_Fsharp_or_Gb + Total_Asharp_or_Bb + Total_Gsharp_or_Ab +
				Total_Dsharp_or_Eb + Total_Csharp_or_Db) == 0) {
			/* No black notes detected, Set KEY to C */
			setKEYval("C");
			setAccidentalVal("None");
			
		}
		else if ((CKeyWeight > GKeyWeight) &&
				 (CKeyWeight > DKeyWeight) &&
				 (CKeyWeight > AKeyWeight) &&
				 (CKeyWeight > EKeyWeight) &&
				 (CKeyWeight > BKeyWeight) &&
				 (CKeyWeight > FsharpKeyWeight) &&
				 (CKeyWeight > CsharpKeyWeight) &&
				 (CKeyWeight > FKeyWeight) &&
				 (CKeyWeight > BbKeyWeight) &&
				 (CKeyWeight > EbKeyWeight) &&
				 (CKeyWeight > AbKeyWeight) &&
				 (CKeyWeight > DbKeyWeight) &&
				 (CKeyWeight > GbKeyWeight) &&
				 (CKeyWeight > CbKeyWeight)) {
			setKEYval("C");
			setAccidentalVal("#");
		}
		else if ((GKeyWeight > CKeyWeight) &&
				 (GKeyWeight > DKeyWeight) &&
				 (GKeyWeight > AKeyWeight) &&
				 (GKeyWeight > EKeyWeight) &&
				 (GKeyWeight > BKeyWeight) &&
				 (GKeyWeight > FsharpKeyWeight) &&
				 (GKeyWeight > CsharpKeyWeight) &&
				 (GKeyWeight > FKeyWeight) &&
				 (GKeyWeight > BbKeyWeight) &&
				 (GKeyWeight > EbKeyWeight) &&
				 (GKeyWeight > AbKeyWeight) &&
				 (GKeyWeight > DbKeyWeight) &&
				 (GKeyWeight > GbKeyWeight) &&
				 (GKeyWeight > CbKeyWeight)) {
			setKEYval("G");
			setAccidentalVal("#");
		}
		else if ((DKeyWeight > CKeyWeight) &&
				 (DKeyWeight > GKeyWeight) &&
				 (DKeyWeight > AKeyWeight) &&
				 (DKeyWeight > EKeyWeight) &&
				 (DKeyWeight > BKeyWeight) &&
				 (DKeyWeight > FsharpKeyWeight) &&
				 (DKeyWeight > CsharpKeyWeight) &&
				 (DKeyWeight > FKeyWeight) &&
				 (DKeyWeight > BbKeyWeight) &&
				 (DKeyWeight > EbKeyWeight) &&
				 (DKeyWeight > AbKeyWeight) &&
				 (DKeyWeight > DbKeyWeight) &&
				 (DKeyWeight > GbKeyWeight) &&
				 (DKeyWeight > CbKeyWeight)) {
			setKEYval("D");
			setAccidentalVal("#");
		}
		else if ((AKeyWeight > CKeyWeight) &&
				 (AKeyWeight > GKeyWeight) &&
				 (AKeyWeight > DKeyWeight) &&
				 (AKeyWeight > EKeyWeight) &&
				 (AKeyWeight > BKeyWeight) &&
				 (AKeyWeight > FsharpKeyWeight) &&
				 (AKeyWeight > CsharpKeyWeight) &&
				 (AKeyWeight > FKeyWeight) &&
				 (AKeyWeight > BbKeyWeight) &&
				 (AKeyWeight > EbKeyWeight) &&
				 (AKeyWeight > AbKeyWeight) &&
				 (AKeyWeight > DbKeyWeight) &&
				 (AKeyWeight > GbKeyWeight) &&
				 (AKeyWeight > CbKeyWeight)) {
			setKEYval("D");
			setAccidentalVal("#");
		}
		else if ((EKeyWeight > CKeyWeight) &&
				 (EKeyWeight > GKeyWeight) &&
				 (EKeyWeight > DKeyWeight) &&
				 (EKeyWeight > AKeyWeight) &&
				 (EKeyWeight > BKeyWeight) &&
				 (EKeyWeight > FsharpKeyWeight) &&
				 (EKeyWeight > CsharpKeyWeight) &&
				 (EKeyWeight > FKeyWeight) &&
				 (EKeyWeight > BbKeyWeight) &&
				 (EKeyWeight > EbKeyWeight) &&
				 (EKeyWeight > AbKeyWeight) &&
				 (EKeyWeight > DbKeyWeight) &&
				 (EKeyWeight > GbKeyWeight) &&
				 (EKeyWeight > CbKeyWeight)) {
			setKEYval("E");
			setAccidentalVal("#");
		}
		else if ((BKeyWeight > CKeyWeight) &&
				 (BKeyWeight > GKeyWeight) &&
				 (BKeyWeight > DKeyWeight) &&
				 (BKeyWeight > AKeyWeight) &&
				 (BKeyWeight > EKeyWeight) &&
				 (BKeyWeight > FsharpKeyWeight) &&
				 (BKeyWeight > CsharpKeyWeight) &&
				 (BKeyWeight > FKeyWeight) &&
				 (BKeyWeight > BbKeyWeight) &&
				 (BKeyWeight > EbKeyWeight) &&
				 (BKeyWeight > AbKeyWeight) &&
				 (BKeyWeight > DbKeyWeight) &&
				 (BKeyWeight > GbKeyWeight) &&
				 (BKeyWeight > CbKeyWeight)) {
			setKEYval("B");
			setAccidentalVal("#");
		}
		else if ((FsharpKeyWeight > CKeyWeight) &&
				 (FsharpKeyWeight > GKeyWeight) &&
				 (FsharpKeyWeight > DKeyWeight) &&
				 (FsharpKeyWeight > AKeyWeight) &&
				 (FsharpKeyWeight > EKeyWeight) &&
				 (FsharpKeyWeight > BKeyWeight) &&
				 (FsharpKeyWeight > CsharpKeyWeight) &&
				 (FsharpKeyWeight > FKeyWeight) &&
				 (FsharpKeyWeight > BbKeyWeight) &&
				 (FsharpKeyWeight > EbKeyWeight) &&
				 (FsharpKeyWeight > AbKeyWeight) &&
				 (FsharpKeyWeight > DbKeyWeight) &&
				 (FsharpKeyWeight > GbKeyWeight) &&
				 (FsharpKeyWeight > CbKeyWeight)) {
			setKEYval("F#");
			setAccidentalVal("#");
		}
		else if ((CsharpKeyWeight > CKeyWeight) &&
				 (CsharpKeyWeight > GKeyWeight) &&
				 (CsharpKeyWeight > DKeyWeight) &&
				 (CsharpKeyWeight > AKeyWeight) &&
				 (CsharpKeyWeight > EKeyWeight) &&
				 (CsharpKeyWeight > BKeyWeight) &&
				 (CsharpKeyWeight > FsharpKeyWeight) &&
				 (CsharpKeyWeight > FKeyWeight) &&
				 (CsharpKeyWeight > BbKeyWeight) &&
				 (CsharpKeyWeight > EbKeyWeight) &&
				 (CsharpKeyWeight > AbKeyWeight) &&
				 (CsharpKeyWeight > DbKeyWeight) &&
				 (CsharpKeyWeight > GbKeyWeight) &&
				 (CsharpKeyWeight > CbKeyWeight)) {
			setKEYval("C#");
			setAccidentalVal("#");
		}
		else if ((FKeyWeight > CKeyWeight) &&
				 (FKeyWeight > GKeyWeight) &&
				 (FKeyWeight > DKeyWeight) &&
				 (FKeyWeight > AKeyWeight) &&
				 (FKeyWeight > EKeyWeight) &&
				 (FKeyWeight > BKeyWeight) &&
				 (FKeyWeight > FsharpKeyWeight) &&
				 (FKeyWeight > CsharpKeyWeight) &&
				 (FKeyWeight > BbKeyWeight) &&
				 (FKeyWeight > EbKeyWeight) &&
				 (FKeyWeight > AbKeyWeight) &&
				 (FKeyWeight > DbKeyWeight) &&
				 (FKeyWeight > GbKeyWeight) &&
				 (FKeyWeight > CbKeyWeight)) {
			setKEYval("F");
			setAccidentalVal("b");
		}
		else if ((BbKeyWeight > CKeyWeight) &&
				 (BbKeyWeight > GKeyWeight) &&
				 (BbKeyWeight > DKeyWeight) &&
				 (BbKeyWeight > AKeyWeight) &&
				 (BbKeyWeight > EKeyWeight) &&
				 (BbKeyWeight > BKeyWeight) &&
				 (BbKeyWeight > FsharpKeyWeight) &&
				 (BbKeyWeight > CsharpKeyWeight) &&
				 (BbKeyWeight > FKeyWeight) &&
				 (BbKeyWeight > EbKeyWeight) &&
				 (BbKeyWeight > AbKeyWeight) &&
				 (BbKeyWeight > DbKeyWeight) &&
				 (BbKeyWeight > GbKeyWeight) &&
				 (BbKeyWeight > CbKeyWeight)) {
			setKEYval("Bb");
			setAccidentalVal("b");
		}
		else if ((EbKeyWeight > CKeyWeight) &&
				 (EbKeyWeight > GKeyWeight) &&
				 (EbKeyWeight > DKeyWeight) &&
				 (EbKeyWeight > AKeyWeight) &&
				 (EbKeyWeight > EKeyWeight) &&
				 (EbKeyWeight > BKeyWeight) &&
				 (EbKeyWeight > FsharpKeyWeight) &&
				 (EbKeyWeight > CsharpKeyWeight) &&
				 (EbKeyWeight > FKeyWeight) &&
				 (EbKeyWeight > BbKeyWeight) &&
				 (EbKeyWeight > AbKeyWeight) &&
				 (EbKeyWeight > DbKeyWeight) &&
				 (EbKeyWeight > GbKeyWeight) &&
				 (EbKeyWeight > CbKeyWeight)) {
			setKEYval("Eb");
			setAccidentalVal("b");
		}
		else if ((AbKeyWeight > CKeyWeight) &&
				 (AbKeyWeight > GKeyWeight) &&
				 (AbKeyWeight > DKeyWeight) &&
				 (AbKeyWeight > AKeyWeight) &&
				 (AbKeyWeight > EKeyWeight) &&
				 (AbKeyWeight > BKeyWeight) &&
				 (AbKeyWeight > FsharpKeyWeight) &&
				 (AbKeyWeight > CsharpKeyWeight) &&
				 (AbKeyWeight > FKeyWeight) &&
				 (AbKeyWeight > BbKeyWeight) &&
				 (AbKeyWeight > EbKeyWeight) &&
				 (AbKeyWeight > DbKeyWeight) &&
				 (AbKeyWeight > GbKeyWeight) &&
				 (AbKeyWeight > CbKeyWeight)) {
			setKEYval("Ab");
			setAccidentalVal("b");
		}
		else if ((DbKeyWeight > CKeyWeight) &&
				 (DbKeyWeight > GKeyWeight) &&
				 (DbKeyWeight > DKeyWeight) &&
				 (DbKeyWeight > AKeyWeight) &&
				 (DbKeyWeight > EKeyWeight) &&
				 (DbKeyWeight > BKeyWeight) &&
				 (DbKeyWeight > FsharpKeyWeight) &&
				 (DbKeyWeight > CsharpKeyWeight) &&
				 (DbKeyWeight > FKeyWeight) &&
				 (DbKeyWeight > BbKeyWeight) &&
				 (DbKeyWeight > EbKeyWeight) &&
				 (DbKeyWeight > AbKeyWeight) &&
				 (DbKeyWeight > GbKeyWeight) &&
				 (DbKeyWeight > CbKeyWeight)) {
			setKEYval("Db");
			setAccidentalVal("b");
		}
		else if ((GbKeyWeight > CKeyWeight) &&
				 (GbKeyWeight > GKeyWeight) &&
				 (GbKeyWeight > DKeyWeight) &&
				 (GbKeyWeight > AKeyWeight) &&
				 (GbKeyWeight > EKeyWeight) &&
				 (GbKeyWeight > BKeyWeight) &&
				 (GbKeyWeight > FsharpKeyWeight) &&
				 (GbKeyWeight > CsharpKeyWeight) &&
				 (GbKeyWeight > FKeyWeight) &&
				 (GbKeyWeight > BbKeyWeight) &&
				 (GbKeyWeight > EbKeyWeight) &&
				 (GbKeyWeight > AbKeyWeight) &&
				 (GbKeyWeight > DbKeyWeight) &&
				 (GbKeyWeight > CbKeyWeight)) {
			setKEYval("Gb");
			setAccidentalVal("b");
		}
		else if ((CbKeyWeight > CKeyWeight) &&
				 (CbKeyWeight > GKeyWeight) &&
				 (CbKeyWeight > DKeyWeight) &&
				 (CbKeyWeight > AKeyWeight) &&
				 (CbKeyWeight > EKeyWeight) &&
				 (CbKeyWeight > BKeyWeight) &&
				 (CbKeyWeight > FsharpKeyWeight) &&
				 (CbKeyWeight > CsharpKeyWeight) &&
				 (CbKeyWeight > FKeyWeight) &&
				 (CbKeyWeight > BbKeyWeight) &&
				 (CbKeyWeight > EbKeyWeight) &&
				 (CbKeyWeight > AbKeyWeight) &&
				 (CbKeyWeight > DbKeyWeight) &&
				 (CbKeyWeight > GbKeyWeight)) {
			setKEYval("Cb");
			setAccidentalVal("b");
		}
		
		/* If Key is still unknown - apply next criteria to identify key          */
		if (getKEYval() == "Unknown") {
			
			/* If Key unknown then Accidental is also unknown as they are set in pair */
			/* Find accidental by proximity criteria */
			if (Total_Fsharp_or_Gb > 0) {
				/* F#/Gb notes detected */
				if (Total_F > Total_G) {
					setAccidentalVal("b");
				}
				else if (Total_G > Total_F) {
					setAccidentalVal("#");
				}
			}
			
			/* Apply next criteria to find accidental if still unknown */
			if (getAccidentalVal() == "Unknown") {
				if ((Total_F > Total_B) &&
					(Total_F > Total_E) &&
					(Total_F > Total_C)) {
					/* F natural is present in greater number than B, E and C */
					setAccidentalVal("b");
				}
				else if ((Total_B > Total_F) &&
					(Total_B > Total_E) &&
					(Total_B > Total_C)) {
					/* B natural is present in greater number than F, E and C */
					setAccidentalVal("#");
				}
				else if ((Total_E > Total_F) &&
					(Total_E > Total_B) &&
					(Total_E > Total_C)) {
					/* E natural is present in greater number than F, B and C */
					if ((Total_Dsharp_or_Eb > 0) ||
						(Total_Gsharp_or_Ab > 0) ||
						(Total_Csharp_or_Db > 0) ||
						(Total_Fsharp_or_Gb > 0)) {
						setAccidentalVal("#");
					}
				}
				else if ((Total_C > Total_F) &&
					(Total_C > Total_B) &&
					(Total_C > Total_E)) {
					/* C natural is present in greater number than F, B and E */
					if ((Total_Csharp_or_Db > 0) ||
						(Total_Gsharp_or_Ab > 0) ||
						(Total_Dsharp_or_Eb > 0) ||
						(Total_Asharp_or_Bb > 0)) {
						setAccidentalVal("b");
					}
				}
			}
			
			/* Apply next criteria to find accidental if still unknown */
			if (getAccidentalVal() == "Unknown") {
				/* Based on likelihood - circle of fifths */
				if (Total_Fsharp_or_Gb > Total_Asharp_or_Bb) {
					setAccidentalVal("#");
				}
				else if (Total_Asharp_or_Bb > Total_Fsharp_or_Gb) {
					setAccidentalVal("b");
				}
				else if (Total_Csharp_or_Db > Total_Dsharp_or_Eb) {
					setAccidentalVal("#");
				}
				else if (Total_Dsharp_or_Eb > Total_Csharp_or_Db) {
					setAccidentalVal("b");
				}
			}
			
			/* Try to find Key using accidental (if accidental found) */
			if (getAccidentalVal() == "#") {
				/* Possible Keys = G, D, A, E, B, F# or C# */
				if ((GKeyWeight > DKeyWeight) &&
					(GKeyWeight > AKeyWeight) &&
					(GKeyWeight > EKeyWeight) &&
					(GKeyWeight > BKeyWeight) &&
					(GKeyWeight > FsharpKeyWeight) &&
					(GKeyWeight > CsharpKeyWeight)) {
					setKEYval("G");
				}
				else if ((DKeyWeight > GKeyWeight) &&
					(DKeyWeight > AKeyWeight) &&
					(DKeyWeight > EKeyWeight) &&
					(DKeyWeight > BKeyWeight) &&
					(DKeyWeight > FsharpKeyWeight) &&
					(DKeyWeight > CsharpKeyWeight)) {
					setKEYval("D");
				}
				else if ((AKeyWeight > GKeyWeight) &&
					(AKeyWeight > DKeyWeight) &&
					(AKeyWeight > EKeyWeight) &&
					(AKeyWeight > BKeyWeight) &&
					(AKeyWeight > FsharpKeyWeight) &&
					(AKeyWeight > CsharpKeyWeight)) {
					setKEYval("A");
				}
				else if ((EKeyWeight > GKeyWeight) &&
					(EKeyWeight > DKeyWeight) &&
					(EKeyWeight > AKeyWeight) &&
					(EKeyWeight > BKeyWeight) &&
					(EKeyWeight > FsharpKeyWeight) &&
					(EKeyWeight > CsharpKeyWeight)) {
					setKEYval("E");
				}
				else if ((BKeyWeight > GKeyWeight) &&
					(BKeyWeight > DKeyWeight) &&
					(BKeyWeight > AKeyWeight) &&
					(BKeyWeight > EKeyWeight) &&
					(BKeyWeight > FsharpKeyWeight) &&
					(BKeyWeight > CsharpKeyWeight)) {
					setKEYval("B");
				}
				else if ((FsharpKeyWeight > GKeyWeight) &&
					(FsharpKeyWeight > DKeyWeight) &&
					(FsharpKeyWeight > AKeyWeight) &&
					(FsharpKeyWeight > EKeyWeight) &&
					(FsharpKeyWeight > BKeyWeight) &&
					(FsharpKeyWeight > CsharpKeyWeight)) {
					setKEYval("F#");
				}
				else if ((CsharpKeyWeight > GKeyWeight) &&
					(CsharpKeyWeight > DKeyWeight) &&
					(CsharpKeyWeight > AKeyWeight) &&
					(CsharpKeyWeight > EKeyWeight) &&
					(CsharpKeyWeight > BKeyWeight) &&
					(CsharpKeyWeight > FsharpKeyWeight)) {
					setKEYval("C#");
				}
			}
			
			if (getAccidentalVal() == "b") {
				/* Possible Keys = F, Bb, Eb, Ab, Db, Gb or Cb */
				if ((FKeyWeight > BbKeyWeight) &&
					(FKeyWeight > EbKeyWeight) &&
					(FKeyWeight > AbKeyWeight) &&
					(FKeyWeight > DbKeyWeight) &&
					(FKeyWeight > GbKeyWeight) &&
					(FKeyWeight > CbKeyWeight)) {
					setKEYval("F");
				}
				else if ((BbKeyWeight > FKeyWeight) &&
					(BbKeyWeight > EbKeyWeight) &&
					(BbKeyWeight > AbKeyWeight) &&
					(BbKeyWeight > DbKeyWeight) &&
					(BbKeyWeight > GbKeyWeight) &&
					(BbKeyWeight > CbKeyWeight)) {
					setKEYval("Bb");
				}
				else if ((EbKeyWeight > FKeyWeight) &&
					(EbKeyWeight > BbKeyWeight) &&
					(EbKeyWeight > AbKeyWeight) &&
					(EbKeyWeight > DbKeyWeight) &&
					(EbKeyWeight > GbKeyWeight) &&
					(EbKeyWeight > CbKeyWeight)) {
					setKEYval("Eb");
				}
				else if ((AbKeyWeight > FKeyWeight) &&
					(AbKeyWeight > BbKeyWeight) &&
					(AbKeyWeight > EbKeyWeight) &&
					(AbKeyWeight > DbKeyWeight) &&
					(AbKeyWeight > GbKeyWeight) &&
					(AbKeyWeight > CbKeyWeight)) {
					setKEYval("Ab");
				}
				else if ((DbKeyWeight > FKeyWeight) &&
					(DbKeyWeight > BbKeyWeight) &&
					(DbKeyWeight > EbKeyWeight) &&
					(DbKeyWeight > AbKeyWeight) &&
					(DbKeyWeight > GbKeyWeight) &&
					(DbKeyWeight > CbKeyWeight)) {
					setKEYval("Db");
				}
				else if ((GbKeyWeight > FKeyWeight) &&
					(GbKeyWeight > BbKeyWeight) &&
					(GbKeyWeight > EbKeyWeight) &&
					(GbKeyWeight > AbKeyWeight) &&
					(GbKeyWeight > DbKeyWeight) &&
					(GbKeyWeight > CbKeyWeight)) {
					setKEYval("Gb");
				}
				else if ((CbKeyWeight > FKeyWeight) &&
					(CbKeyWeight > BbKeyWeight) &&
					(CbKeyWeight > EbKeyWeight) &&
					(CbKeyWeight > AbKeyWeight) &&
					(CbKeyWeight > DbKeyWeight) &&
					(CbKeyWeight > GbKeyWeight)) {
					setKEYval("Cb");
				}
			}

			/* Algorithm was unable to detect KEY           */
			/* Set Accidental to Unknown to force #         */
			if (getKEYval() == "Unknown") {
				setAccidentalVal("Unknown");
			}
		}

	} /* DetermineKey() method end */
	
	/********************************************************************/
	/*     SetAccidental method - Replaces ambiguous notes              */
	/********************************************************************/
	public void SetAccidental(ArrayList<String> NoteArray, String Accidental) {
		int Index;
		
		for (Index=0; Index < NoteArray.size(); Index++) {

			if ("A#7_or_Bb7".equals(NoteArray.get(Index))) {
                    if ("#".equals(Accidental)) {
                    	NoteArray.set(Index, "A#7");
                    }
                    else if ("b".equals(Accidental)) {
                    	NoteArray.set(Index, "Bb7");
                    }
                }
                
                if ("G#7_or_Ab7".equals(NoteArray.get(Index))) {
                	if ("#".equals(Accidental)) {
                    	NoteArray.set(Index, "G#7");
                    }
                    else if ("b".equals(Accidental)) {
                    	NoteArray.set(Index, "Ab7");
                    }
                }
                
                if ("F#7_or_Gb7".equals(NoteArray.get(Index))) {
                	if ("#".equals(Accidental)) {
                    	NoteArray.set(Index, "F#7");
                    }
                    else if ("b".equals(Accidental)) {
                    	NoteArray.set(Index, "Gb7");
                    }
                }
                
                if ("D#7_or_Eb7".equals(NoteArray.get(Index))) {
                	if ("#".equals(Accidental)) {
                    	NoteArray.set(Index, "D#7");
                    }
                    else if ("b".equals(Accidental)) {
                    	NoteArray.set(Index, "Eb7");
                    }
                }
                
                if ("C#7_or_Db7".equals(NoteArray.get(Index))) {
                	if ("#".equals(Accidental)) {
                    	NoteArray.set(Index, "C#7");
                    }
                    else if ("b".equals(Accidental)) {
                    	NoteArray.set(Index, "Db7");
                    }
                }
                
                if ("A#6_or_Bb6".equals(NoteArray.get(Index))) {
                	if ("#".equals(Accidental)) {
                    	NoteArray.set(Index, "A#6");
                    }
                    else if ("b".equals(Accidental)) {
                    	NoteArray.set(Index, "Bb6");
                    }
                }
                
                if ("G#6_or_Ab6".equals(NoteArray.get(Index))) {
                	if ("#".equals(Accidental)) {
                    	NoteArray.set(Index, "G#6");
                    }
                    else if ("b".equals(Accidental)) {
                    	NoteArray.set(Index, "Ab6");
                    }
                }
                
                if ("F#6_or_Gb6".equals(NoteArray.get(Index))) {
                	if ("#".equals(Accidental)) {
                    	NoteArray.set(Index, "F#6");
                    }
                    else if ("b".equals(Accidental)) {
                    	NoteArray.set(Index, "Gb6");
                    }
                }
                
                if ("D#6_or_Eb6".equals(NoteArray.get(Index))) {
                	if ("#".equals(Accidental)) {
                    	NoteArray.set(Index, "D#6");
                    }
                    else if ("b".equals(Accidental)) {
                    	NoteArray.set(Index, "Eb6");
                    }
                }
                
                if ("C#6_or_Db6".equals(NoteArray.get(Index))) {
                	if ("#".equals(Accidental)) {
                    	NoteArray.set(Index, "C#6");
                    }
                    else if ("b".equals(Accidental)) {
                    	NoteArray.set(Index, "Db6");
                    }
                }
                
                if ("A#5_or_Bb5".equals(NoteArray.get(Index))) {
                	if ("#".equals(Accidental)) {
                    	NoteArray.set(Index, "A#5");
                    }
                    else if ("b".equals(Accidental)) {
                    	NoteArray.set(Index, "Bb5");
                    }
                }
                
                if ("G#5_or_Ab5".equals(NoteArray.get(Index))) {
                	if ("#".equals(Accidental)) {
                    	NoteArray.set(Index, "G#5");
                    }
                    else if ("b".equals(Accidental)) {
                    	NoteArray.set(Index, "Ab5");
                    }
                }
                
                if ("F#5_or_Gb5".equals(NoteArray.get(Index))) {
                	if ("#".equals(Accidental)) {
                    	NoteArray.set(Index, "F#5");
                    }
                    else if ("b".equals(Accidental)) {
                    	NoteArray.set(Index, "Gb5");
                    }
                }
                
                if ("D#5_or_Eb5".equals(NoteArray.get(Index))) {
                	if ("#".equals(Accidental)) {
                    	NoteArray.set(Index, "D#5");
                    }
                    else if ("b".equals(Accidental)) {
                    	NoteArray.set(Index, "Eb5");
                    }
                }
                
                if ("C#5_or_Db5".equals(NoteArray.get(Index))) {
                	if ("#".equals(Accidental)) {
                    	NoteArray.set(Index, "C#5");
                    }
                    else if ("b".equals(Accidental)) {
                    	NoteArray.set(Index, "Db5");
                    }
                }
                
                if ("A#4_or_Bb4".equals(NoteArray.get(Index))) {
                	if ("#".equals(Accidental)) {
                    	NoteArray.set(Index, "A#4");
                    }
                    else if ("b".equals(Accidental)) {
                    	NoteArray.set(Index, "Bb4");
                    }
                }
                
                if ("G#4_or_Ab4".equals(NoteArray.get(Index))) {
                	if ("#".equals(Accidental)) {
                    	NoteArray.set(Index, "G#4");
                    }
                    else if ("b".equals(Accidental)) {
                    	NoteArray.set(Index, "Ab4");
                    }
                }
                
                if ("F#4_or_Gb4".equals(NoteArray.get(Index))) {
                	if ("#".equals(Accidental)) {
                    	NoteArray.set(Index, "F#4");
                    }
                    else if ("b".equals(Accidental)) {
                    	NoteArray.set(Index, "Gb4");
                    }
                }
                
                if ("D#4_or_Eb4".equals(NoteArray.get(Index))) {
                	if ("#".equals(Accidental)) {
                    	NoteArray.set(Index, "D#4");
                    }
                    else if ("b".equals(Accidental)) {
                    	NoteArray.set(Index, "Eb4");
                    }
                }
                
                if ("C#4_or_Db4".equals(NoteArray.get(Index))) {
                	if ("#".equals(Accidental)) {
                    	NoteArray.set(Index, "C#4");
                    }
                    else if ("b".equals(Accidental)) {
                    	NoteArray.set(Index, "Db4");
                    }
                }
                
                if ("A#3_or_Bb3".equals(NoteArray.get(Index))) {
                	if ("#".equals(Accidental)) {
                    	NoteArray.set(Index, "A#3");
                    }
                    else if ("b".equals(Accidental)) {
                    	NoteArray.set(Index, "Bb3");
                    }
                }
                
                if ("G#3_or_Ab3".equals(NoteArray.get(Index))) {
                	if ("#".equals(Accidental)) {
                    	NoteArray.set(Index, "G#3");
                    }
                    else if ("b".equals(Accidental)) {
                    	NoteArray.set(Index, "Ab3");
                    }
                }
                
                if ("F#3_or_Gb3".equals(NoteArray.get(Index))) {
                	if ("#".equals(Accidental)) {
                    	NoteArray.set(Index, "F#3");
                    }
                    else if ("b".equals(Accidental)) {
                    	NoteArray.set(Index, "Gb3");
                    }
                }
                
                if ("D#3_or_Eb3".equals(NoteArray.get(Index))) {
                	if ("#".equals(Accidental)) {
                    	NoteArray.set(Index, "D#3");
                    }
                    else if ("b".equals(Accidental)) {
                    	NoteArray.set(Index, "Eb3");
                    }
                }
                
                if ("C#3_or_Db3".equals(NoteArray.get(Index))) {
                	if ("#".equals(Accidental)) {
                    	NoteArray.set(Index, "C#3");
                    }
                    else if ("b".equals(Accidental)) {
                    	NoteArray.set(Index, "Db3");
                    }
                }
                
                if ("A#2_or_Bb2".equals(NoteArray.get(Index))) {
                	if ("#".equals(Accidental)) {
                    	NoteArray.set(Index, "A#2");
                    }
                    else if ("b".equals(Accidental)) {
                    	NoteArray.set(Index, "Bb2");
                    }
                }
                
                if ("G#2_or_Ab2".equals(NoteArray.get(Index))) {
                	if ("#".equals(Accidental)) {
                    	NoteArray.set(Index, "G#2");
                    }
                    else if ("b".equals(Accidental)) {
                    	NoteArray.set(Index, "Ab2");
                    }
                }
                
                if ("F#2_or_Gb2".equals(NoteArray.get(Index))) {
                	if ("#".equals(Accidental)) {
                    	NoteArray.set(Index, "F#2");
                    }
                    else if ("b".equals(Accidental)) {
                    	NoteArray.set(Index, "Gb2");
                    }
                }
                
                if ("D#2_or_Eb2".equals(NoteArray.get(Index))) {
                	if ("#".equals(Accidental)) {
                    	NoteArray.set(Index, "D#2");
                    }
                    else if ("b".equals(Accidental)) {
                    	NoteArray.set(Index, "Eb2");
                    }
                }
                
                if ("C#2_or_Db2".equals(NoteArray.get(Index))) {
                	if ("#".equals(Accidental)) {
                    	NoteArray.set(Index, "C#2");
                    }
                    else if ("b".equals(Accidental)) {
                    	NoteArray.set(Index, "Db2");
                    }
                }
                
                if ("A#1_or_Bb1".equals(NoteArray.get(Index))) {
                	if ("#".equals(Accidental)) {
                    	NoteArray.set(Index, "A#1");
                    }
                    else if ("b".equals(Accidental)) {
                    	NoteArray.set(Index, "Bb1");
                    }
                }
                
                if ("G#1_or_Ab1".equals(NoteArray.get(Index))) {
                	if ("#".equals(Accidental)) {
                    	NoteArray.set(Index, "G#1");
                    }
                    else if ("b".equals(Accidental)) {
                    	NoteArray.set(Index, "Ab1");
                    }
                }
                
                if ("F#1_or_Gb1".equals(NoteArray.get(Index))) {
                	if ("#".equals(Accidental)) {
                    	NoteArray.set(Index, "F#1");
                    }
                    else if ("b".equals(Accidental)) {
                    	NoteArray.set(Index, "Gb1");
                    }
                }
                
                if ("D#1_or_Eb1".equals(NoteArray.get(Index))) {
                	if ("#".equals(Accidental)) {
                    	NoteArray.set(Index, "D#1");
                    }
                    else if ("b".equals(Accidental)) {
                    	NoteArray.set(Index, "Eb1");
                    }
                }
                
                if ("C#1_or_Db1".equals(NoteArray.get(Index))) {
                	if ("#".equals(Accidental)) {
                    	NoteArray.set(Index, "C#1");
                    }
                    else if ("b".equals(Accidental)) {
                    	NoteArray.set(Index, "Db1");
                    }
                }
                
                if ("A#0_or_Bb0".equals(NoteArray.get(Index))) {
                	if ("#".equals(Accidental)) {
                    	NoteArray.set(Index, "A#0");
                    }
                    else if ("b".equals(Accidental)) {
                    	NoteArray.set(Index, "Bb0");
                    }
                }
            }
        }   /* SetAccidental() method ends */

}