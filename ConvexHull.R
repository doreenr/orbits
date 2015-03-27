## read java output file


output <- read.table("~/github/orbits/UniversalMaven/output.txt", sep=",", quote="")
outputround <- round(output,1)
#convhulln(outputround, options = "Qs")
#delaunayn(output, options = "", full = FALSE)
print(output$V1)



# require(akima) ; #require(rgl)
# x=runif(1000)
# y=runif(1000)
# z=rnorm(1000)
# s=interp(x,y,z)
# surface3d(s$x,s$y,s$z)
