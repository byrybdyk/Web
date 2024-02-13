<template>
    <div class="header">
        <div class="header-container">
            <a class="header-item" href="https://se.ifmo.ru/~s353349/lb1/index.htm" target="_blank"> <h2>Lab Work 1</h2></a>
            <a class="header-item" href="https://isu.ifmo.ru/pls/apex/f?p=2437:7:109555636440371:::::" target="_blank"> <h2>Zarubov E.N</h2></a>
            <a class="header-item" href="https://isu.ifmo.ru/pls/apex/f?p=2143:GR:109555636440371::NO:RP:GR_GR,GR_DATE:P3218," target="_blank"> <h2>Group P3218</h2></a>
            <a class="header-item" href="https://se.ifmo.ru/courses/web" target="_blank"> <h2>Variant 2802</h2></a>
        </div>
        <div class="git-logo">
            <a href="https://github.com/byrybdyk/Web" target="_blank">
                <img class="git-img" src="@/images/GitHublogo.png"  alt="git-logo">
            </a>
        </div>
    </div>

</template>

<script>
export default {
  name: "Header"
}
</script>

<style scoped>

.header{
    padding-left: 304px;
    width: 1096px;
    height: 45px;
    padding-top: 20px;
    display: flex;
    align-items: center;
}

.header-container{

    display: flex;
    align-items: center;

}

.header-item{
    margin-right: 57px;
    color: black;
    text-decoration: none;
    font-weight: 700;
    transition: color 0.2s linear;
}

.header-item:hover{
    color: #FF1A00;
}

.git-logo{
    position: relative;
    padding-right: 1px;
    padding-left: 40px;
}


</style>