#include <metal_stdlib>
using namespace metal;
struct Struct_5 {
    uint atomic_scalar;
    array<int, 2> atomic_arr;
};
struct Struct_12 {
    uint old_value;
    char _pad0[4];
    bool exchanged;
};
struct Struct_13 {
    int old_value;
    char _pad0[4];
    bool exchanged;
};
uint global_2;
array<int, 2> global_3;
Struct_5 global_5;

[[kernel]]
void cs_main(uint3 id [[thread_position_in_threadgroup]], uint global_0 [[buffer(0)]], array<int, 2> global_1 [[buffer(1)]], Struct_5 global_4 [[buffer(2)]]) {
    atomicStore(&global_0, 1u);
    atomicStore(&global_1[1], 1);
    atomicStore(&global_4.atomic_scalar, 1u);
    atomicStore(&global_4.atomic_arr[1], 1);
    atomicStore(&global_2, 1u);
    atomicStore(&global_3[1], 1);
    atomicStore(&global_5.atomic_scalar, 1u);
    atomicStore(&global_5.atomic_arr[1], 1);
    workgroupBarrier();
    uint local_0 = atomicLoad(&global_0);
    int local_1 = atomicLoad(&global_1[1]);
    uint local_2 = atomicLoad(&global_4.atomic_scalar);
    int local_3 = atomicLoad(&global_4.atomic_arr[1]);
    uint local_4 = atomicLoad(&global_2);
    int local_5 = atomicLoad(&global_3[1]);
    uint local_6 = atomicLoad(&global_5.atomic_scalar);
    int local_7 = atomicLoad(&global_5.atomic_arr[1]);
    workgroupBarrier();
    atomicAdd(&global_0, 1u);
    atomicAdd(&global_1[1], 1);
    atomicAdd(&global_4.atomic_scalar, 1u);
    atomicAdd(&global_4.atomic_arr[1], 1);
    atomicAdd(&global_2, 1u);
    atomicAdd(&global_3[1], 1);
    atomicAdd(&global_5.atomic_scalar, 1u);
    atomicAdd(&global_5.atomic_arr[1], 1);
    workgroupBarrier();
    atomicSub(&global_0, 1u);
    atomicSub(&global_1[1], 1);
    atomicSub(&global_4.atomic_scalar, 1u);
    atomicSub(&global_4.atomic_arr[1], 1);
    atomicSub(&global_2, 1u);
    atomicSub(&global_3[1], 1);
    atomicSub(&global_5.atomic_scalar, 1u);
    atomicSub(&global_5.atomic_arr[1], 1);
    workgroupBarrier();
    atomicMax(&global_0, 1u);
    atomicMax(&global_1[1], 1);
    atomicMax(&global_4.atomic_scalar, 1u);
    atomicMax(&global_4.atomic_arr[1], 1);
    atomicMax(&global_2, 1u);
    atomicMax(&global_3[1], 1);
    atomicMax(&global_5.atomic_scalar, 1u);
    atomicMax(&global_5.atomic_arr[1], 1);
    workgroupBarrier();
    atomicMin(&global_0, 1u);
    atomicMin(&global_1[1], 1);
    atomicMin(&global_4.atomic_scalar, 1u);
    atomicMin(&global_4.atomic_arr[1], 1);
    atomicMin(&global_2, 1u);
    atomicMin(&global_3[1], 1);
    atomicMin(&global_5.atomic_scalar, 1u);
    atomicMin(&global_5.atomic_arr[1], 1);
    workgroupBarrier();
    atomicAnd(&global_0, 1u);
    atomicAnd(&global_1[1], 1);
    atomicAnd(&global_4.atomic_scalar, 1u);
    atomicAnd(&global_4.atomic_arr[1], 1);
    atomicAnd(&global_2, 1u);
    atomicAnd(&global_3[1], 1);
    atomicAnd(&global_5.atomic_scalar, 1u);
    atomicAnd(&global_5.atomic_arr[1], 1);
    workgroupBarrier();
    atomicOr(&global_0, 1u);
    atomicOr(&global_1[1], 1);
    atomicOr(&global_4.atomic_scalar, 1u);
    atomicOr(&global_4.atomic_arr[1], 1);
    atomicOr(&global_2, 1u);
    atomicOr(&global_3[1], 1);
    atomicOr(&global_5.atomic_scalar, 1u);
    atomicOr(&global_5.atomic_arr[1], 1);
    workgroupBarrier();
    atomicXor(&global_0, 1u);
    atomicXor(&global_1[1], 1);
    atomicXor(&global_4.atomic_scalar, 1u);
    atomicXor(&global_4.atomic_arr[1], 1);
    atomicXor(&global_2, 1u);
    atomicXor(&global_3[1], 1);
    atomicXor(&global_5.atomic_scalar, 1u);
    atomicXor(&global_5.atomic_arr[1], 1);
    atomicExchange(&global_0, 1u);
    atomicExchange(&global_1[1], 1);
    atomicExchange(&global_4.atomic_scalar, 1u);
    atomicExchange(&global_4.atomic_arr[1], 1);
    atomicExchange(&global_2, 1u);
    atomicExchange(&global_3[1], 1);
    atomicExchange(&global_5.atomic_scalar, 1u);
    atomicExchange(&global_5.atomic_arr[1], 1);
    Struct_12 local_8 = atomicCompareExchangeWeak(&global_0, 1u, 2u);
    Struct_13 local_9 = atomicCompareExchangeWeak(&global_1[1], 1, 2);
    Struct_12 local_10 = atomicCompareExchangeWeak(&global_4.atomic_scalar, 1u, 2u);
    Struct_13 local_11 = atomicCompareExchangeWeak(&global_4.atomic_arr[1], 1, 2);
    Struct_12 local_12 = atomicCompareExchangeWeak(&global_2, 1u, 2u);
    Struct_13 local_13 = atomicCompareExchangeWeak(&global_3[1], 1, 2);
    Struct_12 local_14 = atomicCompareExchangeWeak(&global_5.atomic_scalar, 1u, 2u);
    Struct_13 local_15 = atomicCompareExchangeWeak(&global_5.atomic_arr[1], 1, 2);
}

void atomicStore(/* unknown type */ void arg_0, uint arg_1) {
}

void atomicStore(/* unknown type */ void arg_0, int arg_1) {
}

void atomicStore(/* unknown type */ void arg_0, uint arg_1) {
}

void atomicStore(/* unknown type */ void arg_0, int arg_1) {
}

void atomicStore(/* unknown type */ void arg_0, uint arg_1) {
}

void atomicStore(/* unknown type */ void arg_0, int arg_1) {
}

void atomicStore(/* unknown type */ void arg_0, uint arg_1) {
}

void atomicStore(/* unknown type */ void arg_0, int arg_1) {
}

void workgroupBarrier() {
}

uint atomicLoad(/* unknown type */ void arg_0) {
}

int atomicLoad(/* unknown type */ void arg_0) {
}

uint atomicLoad(/* unknown type */ void arg_0) {
}

int atomicLoad(/* unknown type */ void arg_0) {
}

uint atomicLoad(/* unknown type */ void arg_0) {
}

int atomicLoad(/* unknown type */ void arg_0) {
}

uint atomicLoad(/* unknown type */ void arg_0) {
}

int atomicLoad(/* unknown type */ void arg_0) {
}

void workgroupBarrier() {
}

uint atomicAdd(/* unknown type */ void arg_0, uint arg_1) {
}

int atomicAdd(/* unknown type */ void arg_0, int arg_1) {
}

uint atomicAdd(/* unknown type */ void arg_0, uint arg_1) {
}

int atomicAdd(/* unknown type */ void arg_0, int arg_1) {
}

uint atomicAdd(/* unknown type */ void arg_0, uint arg_1) {
}

int atomicAdd(/* unknown type */ void arg_0, int arg_1) {
}

uint atomicAdd(/* unknown type */ void arg_0, uint arg_1) {
}

int atomicAdd(/* unknown type */ void arg_0, int arg_1) {
}

void workgroupBarrier() {
}

uint atomicSub(/* unknown type */ void arg_0, uint arg_1) {
}

int atomicSub(/* unknown type */ void arg_0, int arg_1) {
}

uint atomicSub(/* unknown type */ void arg_0, uint arg_1) {
}

int atomicSub(/* unknown type */ void arg_0, int arg_1) {
}

uint atomicSub(/* unknown type */ void arg_0, uint arg_1) {
}

int atomicSub(/* unknown type */ void arg_0, int arg_1) {
}

uint atomicSub(/* unknown type */ void arg_0, uint arg_1) {
}

int atomicSub(/* unknown type */ void arg_0, int arg_1) {
}

void workgroupBarrier() {
}

uint atomicMax(/* unknown type */ void arg_0, uint arg_1) {
}

int atomicMax(/* unknown type */ void arg_0, int arg_1) {
}

uint atomicMax(/* unknown type */ void arg_0, uint arg_1) {
}

int atomicMax(/* unknown type */ void arg_0, int arg_1) {
}

uint atomicMax(/* unknown type */ void arg_0, uint arg_1) {
}

int atomicMax(/* unknown type */ void arg_0, int arg_1) {
}

uint atomicMax(/* unknown type */ void arg_0, uint arg_1) {
}

int atomicMax(/* unknown type */ void arg_0, int arg_1) {
}

void workgroupBarrier() {
}

uint atomicMin(/* unknown type */ void arg_0, uint arg_1) {
}

int atomicMin(/* unknown type */ void arg_0, int arg_1) {
}

uint atomicMin(/* unknown type */ void arg_0, uint arg_1) {
}

int atomicMin(/* unknown type */ void arg_0, int arg_1) {
}

uint atomicMin(/* unknown type */ void arg_0, uint arg_1) {
}

int atomicMin(/* unknown type */ void arg_0, int arg_1) {
}

uint atomicMin(/* unknown type */ void arg_0, uint arg_1) {
}

int atomicMin(/* unknown type */ void arg_0, int arg_1) {
}

void workgroupBarrier() {
}

uint atomicAnd(/* unknown type */ void arg_0, uint arg_1) {
}

int atomicAnd(/* unknown type */ void arg_0, int arg_1) {
}

uint atomicAnd(/* unknown type */ void arg_0, uint arg_1) {
}

int atomicAnd(/* unknown type */ void arg_0, int arg_1) {
}

uint atomicAnd(/* unknown type */ void arg_0, uint arg_1) {
}

int atomicAnd(/* unknown type */ void arg_0, int arg_1) {
}

uint atomicAnd(/* unknown type */ void arg_0, uint arg_1) {
}

int atomicAnd(/* unknown type */ void arg_0, int arg_1) {
}

void workgroupBarrier() {
}

uint atomicOr(/* unknown type */ void arg_0, uint arg_1) {
}

int atomicOr(/* unknown type */ void arg_0, int arg_1) {
}

uint atomicOr(/* unknown type */ void arg_0, uint arg_1) {
}

int atomicOr(/* unknown type */ void arg_0, int arg_1) {
}

uint atomicOr(/* unknown type */ void arg_0, uint arg_1) {
}

int atomicOr(/* unknown type */ void arg_0, int arg_1) {
}

uint atomicOr(/* unknown type */ void arg_0, uint arg_1) {
}

int atomicOr(/* unknown type */ void arg_0, int arg_1) {
}

void workgroupBarrier() {
}

uint atomicXor(/* unknown type */ void arg_0, uint arg_1) {
}

int atomicXor(/* unknown type */ void arg_0, int arg_1) {
}

uint atomicXor(/* unknown type */ void arg_0, uint arg_1) {
}

int atomicXor(/* unknown type */ void arg_0, int arg_1) {
}

uint atomicXor(/* unknown type */ void arg_0, uint arg_1) {
}

int atomicXor(/* unknown type */ void arg_0, int arg_1) {
}

uint atomicXor(/* unknown type */ void arg_0, uint arg_1) {
}

int atomicXor(/* unknown type */ void arg_0, int arg_1) {
}

uint atomicExchange(/* unknown type */ void arg_0, uint arg_1) {
}

int atomicExchange(/* unknown type */ void arg_0, int arg_1) {
}

uint atomicExchange(/* unknown type */ void arg_0, uint arg_1) {
}

int atomicExchange(/* unknown type */ void arg_0, int arg_1) {
}

uint atomicExchange(/* unknown type */ void arg_0, uint arg_1) {
}

int atomicExchange(/* unknown type */ void arg_0, int arg_1) {
}

uint atomicExchange(/* unknown type */ void arg_0, uint arg_1) {
}

int atomicExchange(/* unknown type */ void arg_0, int arg_1) {
}

Struct_12 atomicCompareExchangeWeak(/* unknown type */ void arg_0, uint arg_1, uint arg_2) {
}

Struct_13 atomicCompareExchangeWeak(/* unknown type */ void arg_0, int arg_1, int arg_2) {
}

Struct_12 atomicCompareExchangeWeak(/* unknown type */ void arg_0, uint arg_1, uint arg_2) {
}

Struct_13 atomicCompareExchangeWeak(/* unknown type */ void arg_0, int arg_1, int arg_2) {
}

Struct_12 atomicCompareExchangeWeak(/* unknown type */ void arg_0, uint arg_1, uint arg_2) {
}

Struct_13 atomicCompareExchangeWeak(/* unknown type */ void arg_0, int arg_1, int arg_2) {
}

Struct_12 atomicCompareExchangeWeak(/* unknown type */ void arg_0, uint arg_1, uint arg_2) {
}

Struct_13 atomicCompareExchangeWeak(/* unknown type */ void arg_0, int arg_1, int arg_2) {
}
