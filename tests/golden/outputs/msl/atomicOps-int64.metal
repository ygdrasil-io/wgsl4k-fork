#include <metal_stdlib>
using namespace metal;
struct Struct_5 {
    ulong atomic_scalar;
    array<long, 2> atomic_arr;
};
struct Struct_13 {
    ulong old_value;
    char _pad0[4];
    bool exchanged;
};
struct Struct_14 {
    long old_value;
    char _pad0[4];
    bool exchanged;
};
ulong global_2;
array<long, 2> global_3;
Struct_5 global_5;

[[kernel]]
void cs_main(uint3 id [[thread_position_in_threadgroup]], ulong global_0 [[buffer(0)]], array<long, 2> global_1 [[buffer(1)]], Struct_5 global_4 [[buffer(2)]]) {
    atomicStore(&global_0, 1u);
    atomicStore(&global_1[1], 1);
    atomicStore(&global_4.atomic_scalar, 1u);
    atomicStore(&global_4.atomic_arr[1], 1);
    atomicStore(&global_2, 1u);
    atomicStore(&global_3[1], 1);
    atomicStore(&global_5.atomic_scalar, 1u);
    atomicStore(&global_5.atomic_arr[1], 1);
    workgroupBarrier();
    ulong local_0 = atomicLoad(&global_0);
    long local_1 = atomicLoad(&global_1[1]);
    ulong local_2 = atomicLoad(&global_4.atomic_scalar);
    long local_3 = atomicLoad(&global_4.atomic_arr[1]);
    ulong local_4 = atomicLoad(&global_2);
    long local_5 = atomicLoad(&global_3[1]);
    ulong local_6 = atomicLoad(&global_5.atomic_scalar);
    long local_7 = atomicLoad(&global_5.atomic_arr[1]);
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
    Struct_13 local_8 = atomicCompareExchangeWeak(&global_0, 1u, 2u);
    Struct_14 local_9 = atomicCompareExchangeWeak(&global_1[1], 1, 2);
    Struct_13 local_10 = atomicCompareExchangeWeak(&global_4.atomic_scalar, 1u, 2u);
    Struct_14 local_11 = atomicCompareExchangeWeak(&global_4.atomic_arr[1], 1, 2);
    Struct_13 local_12 = atomicCompareExchangeWeak(&global_2, 1u, 2u);
    Struct_14 local_13 = atomicCompareExchangeWeak(&global_3[1], 1, 2);
    Struct_13 local_14 = atomicCompareExchangeWeak(&global_5.atomic_scalar, 1u, 2u);
    Struct_14 local_15 = atomicCompareExchangeWeak(&global_5.atomic_arr[1], 1, 2);
}

void atomicStore(/* unknown type */ void arg_0, ulong arg_1) {
}

void atomicStore(/* unknown type */ void arg_0, long arg_1) {
}

void atomicStore(/* unknown type */ void arg_0, ulong arg_1) {
}

void atomicStore(/* unknown type */ void arg_0, long arg_1) {
}

void atomicStore(/* unknown type */ void arg_0, ulong arg_1) {
}

void atomicStore(/* unknown type */ void arg_0, long arg_1) {
}

void atomicStore(/* unknown type */ void arg_0, ulong arg_1) {
}

void atomicStore(/* unknown type */ void arg_0, long arg_1) {
}

void workgroupBarrier() {
}

ulong atomicLoad(/* unknown type */ void arg_0) {
}

long atomicLoad(/* unknown type */ void arg_0) {
}

ulong atomicLoad(/* unknown type */ void arg_0) {
}

long atomicLoad(/* unknown type */ void arg_0) {
}

ulong atomicLoad(/* unknown type */ void arg_0) {
}

long atomicLoad(/* unknown type */ void arg_0) {
}

ulong atomicLoad(/* unknown type */ void arg_0) {
}

long atomicLoad(/* unknown type */ void arg_0) {
}

void workgroupBarrier() {
}

ulong atomicAdd(/* unknown type */ void arg_0, ulong arg_1) {
}

long atomicAdd(/* unknown type */ void arg_0, long arg_1) {
}

ulong atomicAdd(/* unknown type */ void arg_0, ulong arg_1) {
}

long atomicAdd(/* unknown type */ void arg_0, long arg_1) {
}

ulong atomicAdd(/* unknown type */ void arg_0, ulong arg_1) {
}

long atomicAdd(/* unknown type */ void arg_0, long arg_1) {
}

ulong atomicAdd(/* unknown type */ void arg_0, ulong arg_1) {
}

long atomicAdd(/* unknown type */ void arg_0, long arg_1) {
}

void workgroupBarrier() {
}

ulong atomicSub(/* unknown type */ void arg_0, ulong arg_1) {
}

long atomicSub(/* unknown type */ void arg_0, long arg_1) {
}

ulong atomicSub(/* unknown type */ void arg_0, ulong arg_1) {
}

long atomicSub(/* unknown type */ void arg_0, long arg_1) {
}

ulong atomicSub(/* unknown type */ void arg_0, ulong arg_1) {
}

long atomicSub(/* unknown type */ void arg_0, long arg_1) {
}

ulong atomicSub(/* unknown type */ void arg_0, ulong arg_1) {
}

long atomicSub(/* unknown type */ void arg_0, long arg_1) {
}

void workgroupBarrier() {
}

ulong atomicMax(/* unknown type */ void arg_0, ulong arg_1) {
}

long atomicMax(/* unknown type */ void arg_0, long arg_1) {
}

ulong atomicMax(/* unknown type */ void arg_0, ulong arg_1) {
}

long atomicMax(/* unknown type */ void arg_0, long arg_1) {
}

ulong atomicMax(/* unknown type */ void arg_0, ulong arg_1) {
}

long atomicMax(/* unknown type */ void arg_0, long arg_1) {
}

ulong atomicMax(/* unknown type */ void arg_0, ulong arg_1) {
}

long atomicMax(/* unknown type */ void arg_0, long arg_1) {
}

void workgroupBarrier() {
}

ulong atomicMin(/* unknown type */ void arg_0, ulong arg_1) {
}

long atomicMin(/* unknown type */ void arg_0, long arg_1) {
}

ulong atomicMin(/* unknown type */ void arg_0, ulong arg_1) {
}

long atomicMin(/* unknown type */ void arg_0, long arg_1) {
}

ulong atomicMin(/* unknown type */ void arg_0, ulong arg_1) {
}

long atomicMin(/* unknown type */ void arg_0, long arg_1) {
}

ulong atomicMin(/* unknown type */ void arg_0, ulong arg_1) {
}

long atomicMin(/* unknown type */ void arg_0, long arg_1) {
}

void workgroupBarrier() {
}

ulong atomicAnd(/* unknown type */ void arg_0, ulong arg_1) {
}

long atomicAnd(/* unknown type */ void arg_0, long arg_1) {
}

ulong atomicAnd(/* unknown type */ void arg_0, ulong arg_1) {
}

long atomicAnd(/* unknown type */ void arg_0, long arg_1) {
}

ulong atomicAnd(/* unknown type */ void arg_0, ulong arg_1) {
}

long atomicAnd(/* unknown type */ void arg_0, long arg_1) {
}

ulong atomicAnd(/* unknown type */ void arg_0, ulong arg_1) {
}

long atomicAnd(/* unknown type */ void arg_0, long arg_1) {
}

void workgroupBarrier() {
}

ulong atomicOr(/* unknown type */ void arg_0, ulong arg_1) {
}

long atomicOr(/* unknown type */ void arg_0, long arg_1) {
}

ulong atomicOr(/* unknown type */ void arg_0, ulong arg_1) {
}

long atomicOr(/* unknown type */ void arg_0, long arg_1) {
}

ulong atomicOr(/* unknown type */ void arg_0, ulong arg_1) {
}

long atomicOr(/* unknown type */ void arg_0, long arg_1) {
}

ulong atomicOr(/* unknown type */ void arg_0, ulong arg_1) {
}

long atomicOr(/* unknown type */ void arg_0, long arg_1) {
}

void workgroupBarrier() {
}

ulong atomicXor(/* unknown type */ void arg_0, ulong arg_1) {
}

long atomicXor(/* unknown type */ void arg_0, long arg_1) {
}

ulong atomicXor(/* unknown type */ void arg_0, ulong arg_1) {
}

long atomicXor(/* unknown type */ void arg_0, long arg_1) {
}

ulong atomicXor(/* unknown type */ void arg_0, ulong arg_1) {
}

long atomicXor(/* unknown type */ void arg_0, long arg_1) {
}

ulong atomicXor(/* unknown type */ void arg_0, ulong arg_1) {
}

long atomicXor(/* unknown type */ void arg_0, long arg_1) {
}

ulong atomicExchange(/* unknown type */ void arg_0, ulong arg_1) {
}

long atomicExchange(/* unknown type */ void arg_0, long arg_1) {
}

ulong atomicExchange(/* unknown type */ void arg_0, ulong arg_1) {
}

long atomicExchange(/* unknown type */ void arg_0, long arg_1) {
}

ulong atomicExchange(/* unknown type */ void arg_0, ulong arg_1) {
}

long atomicExchange(/* unknown type */ void arg_0, long arg_1) {
}

ulong atomicExchange(/* unknown type */ void arg_0, ulong arg_1) {
}

long atomicExchange(/* unknown type */ void arg_0, long arg_1) {
}

Struct_13 atomicCompareExchangeWeak(/* unknown type */ void arg_0, ulong arg_1, ulong arg_2) {
}

Struct_14 atomicCompareExchangeWeak(/* unknown type */ void arg_0, long arg_1, long arg_2) {
}

Struct_13 atomicCompareExchangeWeak(/* unknown type */ void arg_0, ulong arg_1, ulong arg_2) {
}

Struct_14 atomicCompareExchangeWeak(/* unknown type */ void arg_0, long arg_1, long arg_2) {
}

Struct_13 atomicCompareExchangeWeak(/* unknown type */ void arg_0, ulong arg_1, ulong arg_2) {
}

Struct_14 atomicCompareExchangeWeak(/* unknown type */ void arg_0, long arg_1, long arg_2) {
}

Struct_13 atomicCompareExchangeWeak(/* unknown type */ void arg_0, ulong arg_1, ulong arg_2) {
}

Struct_14 atomicCompareExchangeWeak(/* unknown type */ void arg_0, long arg_1, long arg_2) {
}
