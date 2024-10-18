#!/bin/bash
echo " -- Search Smtp4dev (smtp server mocking)..."
echo "OK"
echo " -- Launch Smtp-dev in new terminal"
# Utilisation de 'open' pour ouvrir un nouveau terminal sur macOS
open -a Terminal .
echo "OK"
echo " -- Launch Springboot REST API project"
# Vérifie si mvn est disponible et exécute la commande si c'est le cas
if command -v mvn &> /dev/null
then
    mvn spring-boot:run
else
    echo "Maven n'est pas installé. Installez Maven et réessayez."
fi
