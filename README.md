# BeatDetector
Detector de Tempo y BPM basado en la libreria TargosDSP de Java.  
Calcula también la nota y la octava de cada uno de los beats tocados a partir de la frecuencia.  
Se utiliza la entrada de micrófono por defecto y sirve para cualquier instrumento o incluso la voz.  
Estima la marca de tiempo (parte inferior, ej: 1/4, 1/2, etc.) y realiza calculos adaptativos.  
Además gracias a una clase realizada por https://github.com/ece1778github/Mozarts-Ear es capaz de realizar el cálculo de la clave en la que se está tocando.  
Muestra esta información en texto plano por consola, que es útil así, pero puede ser más útil especialmente para usarse en otros proyectos.  
Son necesarias las librerías de TargosDSP latest.jar y latest-bin.jar que se encuentran en la carpeta libraries del proyecto.  

Tempo and BPM detector based on the Java TargosDSP library.  
It detects also the note and the octave of each played beat based on the pitch.  
It uses the default microphone input and it's useful for any instrument or even voice.  
It estimates the time signature (inferior part, ex: 1/4, 1/2, etc.) and it takes adaptative measures.  
Also thanks to a class made by https://github.com/ece1778github/Mozarts-Ear it's capable of calculating the key signature in which you are playing.  
It will display all this information in raw text on screen, which is useful as it is, but it can be useful to use especially in other projects.  
TargosDSP latest.jar y latest-bin.jar libraries are needed, they can be found in the project libraries folder.  

# Salida de Ejemplo / Example Output

Beat detectado en: 4.922630310058594 s con frecuencia de: 447.06598 Hz  
Media cuadratica de: 0.27224625144095654 y probabilidad de: 0.81223047  
Tiempo desde el anterior beat: 0.1857595443725586 s  
BPM Totales: 158.4501097633218 bpm  
BPM Crudo: 322.99820826252807 bpm  
BPM Neto: 161.49910413126403 bpm  
Duracion del Beat: 1/8  
Nota MIDI: 69  
La nota tocada es A o La  
Estas tocando en la octava: 5  
Estas tocando en la clave: F  
