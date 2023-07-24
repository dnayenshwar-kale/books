# books
it Spring boot book api developed for Demonstrate java development and deployment in aws cloud via build project and aws pipeline

# sonar cloud  
https://sonarcloud.io/summary/overall?id=dnayenshwar-kale_books

Steps 
# Push code to aws code commit  
lunch cloud9 ide 
git clone  https://github.com/dnayenshwar-kale/books.git 
ls 
Create repo books  and  clone it 
git clone https://git-codecommit.us-east-1.amazonaws.com/v1/repos/books-repo 
p –rf book/* book-repo 
cd books-repo 
git add . 
git commit -m "init" 
git push -u origin  master 

# Create ECR repo
# Create cluster, task definitaion and service 
# build project 
1 Sonar scan - sonarqub.yml
2  build image - buildspec.yml

# build pipeline with 4 stage 
1 download code 
2 unit test and sonar scan 
3 build and push docker image
4 deploy image on ecs service/task 

# test api using swager ui 

http://localhost:8080/swagger-ui/index.html

# create api gateway 
1 copy json from http://localhost:8080/v3/api-docs
2 create api from above swagger api 
3  integrate request with http and point like http://localhost:8080/api/book







