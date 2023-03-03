#!/bin/bash

# Set the name of the project and the path to the CHANGELOG.org file
PROJECT_NAME="chord-progression"
CHANGELOG_FILE="CHANGELOG.org"

# Get the latest version number from the project.clj file
VERSION=$(grep -m1 "defproject" project.clj | awk '{print $3}' | tr -d \")

# Generate a name for the changelog based on the version number and current date
DATE=$(date +%Y-%m-%d)
CHANGELOG_NAME="${PROJECT_NAME} ${VERSION} (${DATE})"

# Create the new CHANGELOG.org file
touch ${CHANGELOG_FILE}
echo "* ${CHANGELOG_NAME}" >> ${CHANGELOG_FILE}
echo "" >> ${CHANGELOG_FILE}
echo "** Changes" >> ${CHANGELOG_FILE}
echo "" >> ${CHANGELOG_FILE}
echo "** Bug Fixes" >> ${CHANGELOG_FILE}
echo "" >> ${CHANGELOG_FILE}
echo "** Features" >> ${CHANGELOG_FILE}
echo "" >> ${CHANGELOG_FILE}

# Open the file in your default editor for further editing
${EDITOR:-nano} ${CHANGELOG_FILE}
