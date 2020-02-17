
/**
 * Array.sort( ) 함수의 파라메터로 사용되어 배열내의 json항목(field)을 기준으로 정렬
 * - 내림차순
 */
module.exports.ArraySortByDesc = function( field){

    return function( a,b){

        return ((a[field] === b[field])?0:((a[field]<b[field])?1 : -1) )
    }
}

/**
 * Array.sort( ) 함수의 파라메터로 사용되어 배열내의 json항목(field)을 기준으로 정렬
 * - 오름차순
 */
module.exports.ArraySortByAsc = function( field){

    return function( a,b){

        return ((a[field] === b[field])?0:((a[field]>b[field])?1 : -1) )
    }
}