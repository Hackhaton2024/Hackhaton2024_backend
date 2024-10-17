#!/usr/bin/bash
# Commit and directly refresh root repository
# T.NGUYEN 2024-17-10
#===================================================================================
echo "
  _    _            _         _   _                 ___   ___ ___  _  _   
 | |  | |          | |       | | | |               |__ \ / _ \__ \| || |  
 | |__| | __ _  ___| | ____ _| |_| |__   ___  _ __    ) | | | | ) | || |_ 
 |  __  |/ _\ |/ __| |/ / _\ | __| '_ \ / _ \| '_ \  / /| | | |/ /|__   _|
 | |  | | (_| | (__|   < (_| | |_| | | | (_) | | | |/ /_| |_| / /_   | |  
 |_|  |_|\__,_|\___|_|\_\__,_|\__|_| |_|\___/|_| |_|____|\___/____|  |_|  
                                                                          
 L'EQUIPE DE CHOC
 « Unissons nos forces pour créer des solutions innovantes et durables »                                                                         
==============================================================================
  Commit and directly refresh root repository
  T.NGUYEN 
  2024-17-10
#=============================================================================
"
git add .
git commit -m"MAJ"
git push
cd ..
git add .
git commit -m"MAJ"
git push