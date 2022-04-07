install:
	./gradlew clean install

run-dist:
	./build/install/app/bin/app

check-updates:
	./gradlew dependencyUpdates

test:
	./gradlew test

run:
	./gradlew clean
	./gradlew run

build:
	./gradlew clean build

report:
	./gradlew jacocoTestReport

.PHONY: build