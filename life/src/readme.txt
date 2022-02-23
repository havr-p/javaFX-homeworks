Celá aplikácia sa skladá zo 4 tried a súboru  styles.css, ktorý obsahuje štylizácie ovládacích prvkov.
Trieda Life je triedou, v ktorej sa spúšťa samotná aplikácia.
Na vytvorenie, nastavenie a oživenie ovládacích prvkov aplikácie slúžia ďalšie 3 triedy:
1. Trieda Simulation: rieši "internú logiku" hry Life. Mriežku buniek implementuje ako 2d-pole, v ktorom sa uchováva stav každej bunky.

2. Trieda InitialScene: rozširuje triedu Scene; vytvára okno, v ktorom používateľ zadá rozmery mriežky.

3. Trieda MainScene: rozširuje triedu Scene; vytvára a nastavuje všetky ovládacie prvky hlavnéj scény v aplikácii: mriežku buniek, tlačidlá apod. Prekresenie okná aplikácie funguje tak, že proste nastavíme pre okno scénu mainScene namiesto InitialScene.