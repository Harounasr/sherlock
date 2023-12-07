#!/usr/bin/env bash

# Author: Leon Föckersperger

set -e

mvn --no-transfer-progress -DSYSTEM_TEST_BROWSER=firefox -DskipUnitTests -DskipIntegerationTests -Psystem-tests clean compile verify
exit_code=$?

echo ""
echo ""
echo "Tomcat logs:"
echo ""
cat "target/tomcat.log"

grep -o '<tfoot>.*</tfoot>' <target/site/jacoco/index.html

exit $exit_code