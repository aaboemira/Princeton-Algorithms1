# Solving percoltion problem using WegihtedQuickUnion Algorithm
  	As a part of Algorithms, Part I Princeton University course.
## Table of Contents
1- [Introduction](#Percolation)
2- [Solution](#Solution)
3- [Topics](#topics)

## Percolation problem
 -Given a composite systems comprised of randomly distributed insulating and metallic materials: what fraction of the materials need to be metallic so that the composite system is an electrical conductor?\n
 -Given a porous landscape with water on the surface (or oil below), under what conditions will the water be able to drain through to the bottom (or the oil to gush through to the surface)? Scientists have defined an abstract process known as percolation to model such situations.
## Solution 
We model a percolation system using an n-by-n grid of sites. Each site is either open or blocked. A full site is an open site that can be connected to an open site in the top row via a chain of neighboring (left, right, up, down) open sites. We say the system percolates if there is a full site in the bottom row.

## topics
-Dynamic Connectivty
-Quick union algorithm


