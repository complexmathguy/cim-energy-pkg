#!/bin/bash

cd $1

git config --global user.email "srandolph6969@gmail.com"
git config --global user.name "Steven Randolph"
 
echo init the repository
git init .

echo add all files from root dir below, with ignore dirs and files in the .gitignore
git add .

echo 'commit all the files'
git commit -m "initial commit"

echo 'add a remote pseudo for the cim-energy-pkg repository'

git remote add origin https://complexmathguy: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@github.com/complexmathguy/cim-energy-pkg

echo 'push the commits to the repository master'
git push origin master


echo 'add tag latest'
git tag latest

echo 'push tag latest'
git push origin latest

